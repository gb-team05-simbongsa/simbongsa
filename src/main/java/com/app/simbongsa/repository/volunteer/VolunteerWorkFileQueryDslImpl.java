//package com.app.simbongsa.repository.volunteer;
//
//import com.app.simbongsa.entity.file.File;
//import com.app.simbongsa.entity.file.QVolunteerWorkFile;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//
//import static com.app.simbongsa.entity.file.QVolunteerWorkFile.volunteerWorkFile;
//
//@RequiredArgsConstructor
//public class VolunteerWorkFileQueryDslImpl implements VolunteerWorkFileQueryDsl {
//    private final JPAQueryFactory query;
//
//    @Override
//    public void updateVolunteerWorkFile(File file) {
//        query.update(volunteerWorkFile)
//                .set(volunteerWorkFile.fileName, file.getFileName())
//                .set(volunteerWorkFile.fileUuid, file.getFileUuid())
//                .set(volunteerWorkFile.filePath, file.getFilePath())
//                .where(volunteerWorkFile.id.eq(file.getId()))
//                .execute();
//    }
//}
