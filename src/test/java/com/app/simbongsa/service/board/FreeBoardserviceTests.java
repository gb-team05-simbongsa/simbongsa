package com.app.simbongsa.service.board;

import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.repository.board.FreeBoardRepository;
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
public class FreeBoardserviceTests {
    @Autowired
    private FreeBoardService freeBoardService;

    /* 유저가 작성한 자유게시물 조회(페이징처리) */
/*    @Test
    public void getMyFreeBoardsTest() {
        UserDetail userDetail = new UserDetail(278L);
        log.info(freeBoardService.getMyFreeBoards(5,userDetail).toString() + "skdhfkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkasf");
    }*/
}
