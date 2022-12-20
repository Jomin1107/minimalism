package com.oracle.minimalism.hjDAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.minimalism.dto.RnQDto;
import com.oracle.minimalism.dto.UserDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MypageDaoImpl implements MypageDao {
	
	private final SqlSession session;

	/* 목록 총갯수 */
	@Override
	public int totalMypageCnt(UserDto userDto) {
		System.out.println("MypageDaoImpl totalMypageCnt Start...");
		int totalMypageCnt = 0;
		try {
			totalMypageCnt = session.selectOne("hjMypageTotal",userDto );
		} catch (Exception e) {
			System.out.println("MypageDaoImpl totalReview Exception Message-> " + e.getMessage());
		}
		
		return totalMypageCnt;
	}

	/* 목록 리스트 */
	@Override
	public List<RnQDto> mypageList(RnQDto rnQDto) {
		System.out.println("MypageDaoImpl mypageList Start...");
		List<RnQDto> mypageList = null;
		
		try {
			mypageList = session.selectList("hjMypageList",rnQDto);
		} catch (Exception e) {
			System.out.println("MypageDaoImpl mypageList Exception Message-> " + e.getMessage());
		}
		return mypageList;
	}
}
