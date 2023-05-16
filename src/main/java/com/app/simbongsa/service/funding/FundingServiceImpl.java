package com.app.simbongsa.service.funding;

import com.app.simbongsa.domain.FileDTO;
import com.app.simbongsa.domain.FundingDTO;
import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.entity.file.FundingFile;
import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.repository.funding.FundingRepository;
import com.app.simbongsa.search.admin.AdminFundingSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("funding") @Primary
@Slf4j
public class FundingServiceImpl implements FundingService {
    private final FundingRepository fundingRepository;

//    메인페이지 펀딩 인기순
//    여기에서 FileDTOs에 파일이 안들어가
    @Override
    public List<FundingDTO> getAllPopularFundingList() {
//        List<Funding> fundings = fundingRepository.findAllWithPopular();
//        fundings.stream().map(Funding::getFundingFile).forEach(v -> log.info(v.toString() + "================== 여기 봐주세요 1 =========="));
//        List<FundingDTO> fundingDTOS = new ArrayList<>();
//        for(Funding funding : fundings){
//            FundingDTO fundingDTO = toFundingDTO(funding);
//            fundingDTOS.add(fundingDTO);
//            fundingDTOS.stream().map(FundingDTO::toString).forEach(log::info);
//        }

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
        fundingDTOS.stream().map(FundingDTO::toString).forEach(v -> log.info(v + " 여기야여기~!~!~!~!~!~!~!~!~!~!~!~!~!~!~!~!~!~!~!~!~!~!"));
        return fundingDTOS;
    }

    // 펀딩 기본정보 저장
    @Override
    public void fundingRegister(FundingDTO fundingDTO) {
//        fundingRepository.save(toFundingEntity(fundingDTO));
    }

    // 펀딩 전체 목록 조회(무한스크롤)
    @Override
    public Slice<FundingDTO> getFundingList(Pageable pageable) {
        Slice<Funding> fundingList = fundingRepository.findAllWithSlice_QueryDsl(pageable);

        List<FundingDTO> fundingDTOS = fundingList.getContent().stream().map(this::toFundingDTO).collect(Collectors.toList());
        return new SliceImpl<>(fundingDTOS, pageable, fundingList.hasNext());

    }

    //펀딩 상세보기 파일빼고 controller 완성
    @Override
    public FundingDTO getFundingDetail(Long fundingId) {
        return toFundingDTO(fundingRepository.findById(fundingId).get());

    }

    @Override
    public Page<FundingDTO> getFunding(Integer page, AdminFundingSearch adminFundingSearch) {
        Page<Funding> fundings = fundingRepository.findAllWithPaging(adminFundingSearch, PageRequest.of(page, 5));
        List<FundingDTO> fundingDTOS = fundings.getContent().stream().map(this::toFundingDTO).collect(Collectors.toList());
        return new PageImpl<>(fundingDTOS, fundings.getPageable(), fundings.getTotalElements());
    }
}

