package com.kh.java.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

// FileRenamePolicy 라는 인터페이스를 구현해서
// 이름바꾸기 정책을 사용
public class MyRenamePolicy implements FileRenamePolicy{

	// FileRenamePolicy 인터페이스가 가지고 있는 rename추상메서드가 있음
	// rename메서드를 오버라이딩해서 기존 파일명을 전달받아서 파일명을 수정한 뒤 
	// 수정한 파일을 반환해줄 것
	@Override
	public File rename(File originFile) {
		
		// "aaa.jpg"
		// "bbb.jpg"
		// "ccc.jpg"
		
		// 원본파일명
		String originName = originFile.getName();
		
		// 개발자정의대로 이름바꾸기 => 최대한 이름 안겹치게
		// KHacademy_년월일시분초_랜덤값+원본파일확장자
		
		/*
		 * 예시)
		 * 원본파일명				변경파일명
		 * bono.jpg		=>		KHacademy_20250930163146_999.jpg
		 */
		
		// 1. 원본파일의 확장자
		 String ext = originName.substring(originName.lastIndexOf(".")); // lastIndexof: 마지막 .위치 인덱스 찾아서 substring: 해당인덱스부터 짜르기
		 
		 // 2. 년월일시분초
		 String currentTime = new SimpleDateFormat("yyyyMMddHHmmss")
				 					.format(new Date());
		 
		 // 3. 랜덤숫자
		 int randomNo = (int)(Math.random()*900) + 100;
		 
		 // 1 + 2 + 3
		 String changeName = "KHacademy_" + currentTime + "_" + randomNo + ext;
		
		 
		return new File(originFile.getParent(), changeName);
	}

	
}
