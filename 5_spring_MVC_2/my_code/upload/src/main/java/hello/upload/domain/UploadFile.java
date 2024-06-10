package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {

    private String uploadFileName; //사용자가 설정한 파일 이름
    private String storeFileName; //실제 서버에 저장될 파일 이름
    //여러 사용자가 동일한 이름의 file을 upload시 서버에는 동일한 파일이름으로 저장될 수 있음
    //=> 사용자가 지정한 이름과 별개로 서버에는 유일한 UUID등을 활용해 파일을 저장해야 함


    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
