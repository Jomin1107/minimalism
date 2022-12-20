package com.oracle.minimalism.hjService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.minimalism.dto.RnQDto;
import com.oracle.minimalism.dto.UserDto;
import com.oracle.minimalism.hjDAO.MypageDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService {

	private final MypageDao mypageDao;

	/* 목록 총갯수 */
	@Override
	public int totalMypageCnt(UserDto userDto) {
		System.out.println("MypageServiceImpl totalMypageCnt Start...");
		int totalMypageCnt = 0;
		totalMypageCnt = mypageDao.totalMypageCnt(userDto);
		System.out.println("MypageServiceImpl totalMypageCnt-> " + totalMypageCnt);
		return totalMypageCnt;
	}

	/* 목록 리스트 */
	@Override
	public List<RnQDto> mypageList(RnQDto rnQDto) {
		System.out.println("MypageServiceImpl mypageList Start...");
		List<RnQDto> mypageList = null;
		mypageList = mypageDao.mypageList(rnQDto);
		System.out.println("MypageServiceImpl mypageList.size-> " + mypageList.size());
		return mypageList;
	}


}
