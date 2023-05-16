package com.app.simbongsa.repository.supportRequest;

import com.app.simbongsa.entity.file.SupportRequestFile;
import com.app.simbongsa.repository.support.SupportRequestFileRepositorys;
import com.app.simbongsa.repository.support.SupportRequestRepository;
import com.app.simbongsa.type.FileRepresentationalType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class SupportRequestFileTests {
    @Autowired
    private SupportRequestFileRepositorys supportRequestFileRepositorys;

    @Autowired
    private SupportRequestRepository supportRequestRepository;

//    @Test
//    public void saveTest() {
//        for (int i = 1; i <= 5; i++) {
//            SupportRequestFile supportRequestFile = new SupportRequestFile("001.png", "34271983dksjf", "c:/2023/document", FileRepresentationalType.REPRESENTATION, supportRequestRepository.findById(441L).get());
//            supportRequestFileRepositorys.save(supportRequestFile);
//        }

//    }
}
