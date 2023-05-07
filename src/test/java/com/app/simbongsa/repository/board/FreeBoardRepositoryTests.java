package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.board.Board;
import com.app.simbongsa.entity.board.FreeBoard;
import com.app.simbongsa.entity.board.FreeBoardReply;
import com.app.simbongsa.entity.board.QFreeBoard;
import com.app.simbongsa.entity.funding.Funding;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static com.app.simbongsa.entity.board.QFreeBoard.freeBoard;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class FreeBoardRepositoryTests {
//    @Autowired
//    private FreeBoardRepository freeBoardRepository;
//
//    @Test
//    public void saveTest(){
//        for(int i= 0; i < 50; i++){
//
//        }
//    }
}
