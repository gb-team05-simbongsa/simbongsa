package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.file.FreeBoardFile;
import com.app.simbongsa.entity.file.ReviewFile;
import com.app.simbongsa.type.FileRepresentationalType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class FreeBoardFileRepositoryTests {
    @Autowired
    private FreeBoardFileRepository freeBoardFileRepository;

    @Autowired
    private FreeBoardRepository freeBoardRepository;

    @Test
    public void saveTest(){
//        FreeBoardFile freeBoardFile1 = new FreeBoardFile(970L,"100" + ".png","48ew34ufeuods","c:/2023/document", FileRepresentationalType.REPRESENTATION,freeBoardRepository.findById(980L).get());
//        freeBoardFileRepository.save(freeBoardFile1);
        for (int i = 1; i <= 5; i++) {
            FreeBoardFile freeBoardFile2 = new FreeBoardFile(i+970L,"00" + i + ".png","34271983dksjf" + i,"c:/2023/document", FileRepresentationalType.NORMAL,freeBoardRepository.findById(1423L).get());
            freeBoardFileRepository.save(freeBoardFile2);
        }
    }
}
