package com.oracle.minimalism.hjService;

import java.util.List;

import com.oracle.minimalism.dto.RnQDto;
import com.oracle.minimalism.dto.UserDto;

public interface MypageService {

	/* 목록 총갯수 */
	int totalMypageCnt(UserDto userDto);
	/* 목록 리스트 */
	List<RnQDto> mypageList(RnQDto rnQDto);



}
