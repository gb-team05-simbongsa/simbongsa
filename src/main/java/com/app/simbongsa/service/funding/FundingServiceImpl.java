package com.app.simbongsa.service.funding;

import com.app.simbongsa.domain.*;
import com.app.simbongsa.entity.board.FreeBoard;
import com.app.simbongsa.entity.file.FreeBoardFile;
import com.app.simbongsa.entity.file.FundingFile;
import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.entity.funding.FundingCreator;
import com.app.simbongsa.entity.funding.FundingPayment;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.entity.support.Support;
import com.app.simbongsa.repository.funding.FundingFileRepository;
import com.app.simbongsa.repository.funding.FundingRepository;
import com.app.simbongsa.search.admin.AdminFundingSearch;
import com.app.simbongsa.type.FileRepresentationalType;
import com.app.simbongsa.type.RequestType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("funding")
@Primary
@Slf4j
public class FundingServiceImpl implements FundingService {
    private final FundingRepository fundingRepository;
    private final FundingFileRepository fundingFileRepository;

    //    메인페이지 펀딩 인기순
//    여기에서 FileDTOs에 파일이 안들어가
    @Override
    public List<FundingDTO> getAllPopularFundingList() {
        List<Funding> fundingList = fundingRepository.findAllWithPopular();
        List<FundingDTO> fundingDTOS = fundingList.stream()
                .map(funding -> {
                    FundingDTO fundingDTO = toFundingDTO(funding);
                    List<FundingFile> fundingFiles = funding.getFundingFile();
                    List<FileDTO> fileDTOs = FileToDTO(fundingFiles);
                    fundingDTO.setFileDTOs(fileDTOs);
                    return fundingDTO;
                })
                .collect(Collectors.toList());
        return fundingDTOS;
    }

    // 펀딩 저장 // 등록한 시점의 현재 시퀀스만들기
    @Override
    public void fundingRegister(FundingDTO fundingDTO) {
        List<FileDTO> fileDTOs = fundingDTO.getFileDTOs();
        fundingRepository.save(toFundingEntity(fundingDTO));

        if (fileDTOs != null) {
            fileDTOs.get(0).setFileRepresentationalType(FileRepresentationalType.REPRESENTATION);
            fileDTOs.get(0).setFunding(getCurrentSequence());
            fundingFileRepository.save(toFundingFileEntity(fileDTOs.get(0)));

            /* FileDTO fileDTO = fundingDTO.getFileDTO();*/
/*
        fundingRepository.save(toFundingEntity(fundingDTO));

        fileDTO.setFileRepresentationalType(FileRepresentationalType.REPRESENTATION);
        fileDTO.setFunding(getCurrentSequence());
        fundingFileRepository.save(toFundingFileEntity(fileDTO));*/
        }
    }
    // 펀딩 전체 목록 조회(무한스크롤)
    @Override
    public Slice<FundingDTO> getFundingList(Pageable pageable) {
        Slice<Funding> fundingList = fundingRepository.findAllWithSlice_QueryDsl(pageable);

        List<FundingDTO> fundingDTOS = fundingList.getContent().stream().map(this::toFundingDTO).collect(Collectors.toList());
        return new SliceImpl<>(fundingDTOS, pageable, fundingList.hasNext());

    }

    @Override
    public Integer getFundingCount(Long fundingId) {
        return fundingRepository.getFundingCount_QueryDsl(fundingId).intValue();
    }

    //펀딩 상세보기 파일빼고 controller 완성
    @Override
    public FundingDTO getFundingDetail(Long fundingId) {
        Optional<Funding> funding = fundingRepository.findByIdForDetail_QueryDsl(fundingId);
        return toFundingDTO(funding.get());

    }

    // 현재 시퀀스 가져오기
    @Override
    public Funding getCurrentSequence() {
        return fundingRepository.getCurrentSequence_QueryDsl();


    }

    /* 유저가 작성한 자유게시물 조회(페이징처리) */
    @Override
    public Page<FundingDTO> getMyFunding(Integer page, MemberDTO memberDTO) {
        Long memberId = memberDTO.getId();
        page = page == null ? 0 : page;
        Page<Funding> myFundings = fundingRepository.findByMemberId_QueryDSL(PageRequest.of(page, 5), memberId);

        List<FundingDTO> myFundingDTOS = myFundings.stream().map(funding -> {
            FundingDTO fundingDTO = toFundingDTO(funding);
            List<FundingFile> FundingFiles = funding.getFundingFile();
            List<FileDTO> fileDTOs = FileToDTO(FundingFiles);
            fundingDTO.setFileDTOs(fileDTOs);
            return fundingDTO;
        }).collect(Collectors.toList());

        log.info("myFreeBoardDTOS serviceImpl: ===== " + myFundingDTOS);
        return new PageImpl<>(myFundingDTOS,myFundings.getPageable(),myFundings.getTotalElements());
    }


    @Override
    public Page<FundingPaymentDTO> getFundingSupportByMemberId(Integer page, Long id) {
        Page<FundingPayment> foundFundingPayment = fundingRepository.findByMemberId(PageRequest.of(page, 5), id);
        List<FundingPaymentDTO> fundingPaymentDTOS = foundFundingPayment.getContent().stream().map(this::toFundingPaymentDTO).collect(Collectors.toList());
        return new PageImpl<>(fundingPaymentDTOS, foundFundingPayment.getPageable(), foundFundingPayment.getTotalElements());
    }

/*    @Override
    public Page<FreeBoardDTO> getFreeForMemberIdList(Pageable pageable, Long id){
        Page<FreeBoard> freeBoards = freeBoardRepository.findAllByFreeMemberIdPaging_QueryDsl(pageable, id);
        List<FreeBoardDTO> freeBoardDTOS = freeBoards.stream().map(this::toFreeBoardDTO).collect(Collectors.toList());
        return new PageImpl<>(freeBoardDTOS, freeBoards.getPageable(), freeBoards.getTotalElements());
    }*/



    @Override
    public Page<FundingDTO> getFunding(Integer page, AdminFundingSearch adminFundingSearch) {
        Page<Funding> fundings = fundingRepository.findAllWithPaging(adminFundingSearch, PageRequest.of(page, 5));
        List<FundingDTO> fundingDTOS = fundings.getContent().stream().map(this::toFundingDTO).collect(Collectors.toList());
        return new PageImpl<>(fundingDTOS, fundings.getPageable(), fundings.getTotalElements());
    }

    @Override
    public void deleteFunding(List<Long> ids) {
        fundingRepository.deleteAllById(ids);
    }

    @Override
    @Transactional
    public void updateFundingStatus(List<Long> ids, RequestType requestType) {
        fundingRepository.updateWaitToAcceptByIds(ids, requestType);
    }

    @Override
    public List<Long> countAcceptAndWait() {
        return Arrays.asList(fundingRepository.findCountAcceptOrWait(RequestType.승인),
                fundingRepository.findCountAcceptOrWait(RequestType.대기),
                fundingRepository.findCountAcceptOrWait(RequestType.반려));
    }

    @Override
    @Transactional
    public void updateFundingPlan(Long fundingId, int fundingTargetPrice, LocalDate fundingStartDate, LocalDate fundingEndDate) {
        fundingRepository.updateFunding_QueryDsl(fundingId, fundingTargetPrice, fundingStartDate, fundingEndDate);
    }


}

