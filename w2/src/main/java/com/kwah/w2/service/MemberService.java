package com.kwah.w2.service;

import com.kwah.w2.dao.MemberDAO;
import com.kwah.w2.domain.MemberVO;
import com.kwah.w2.dto.MemberDTO;
import com.kwah.w2.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

@Log4j2
public enum MemberService {
    INSTANCE;

    private MemberDAO dao;
    private ModelMapper modelMapper;

    MemberService() {
        dao = new MemberDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    // 로그인을 위한 메서드
    public MemberDTO login(String mid, String mpw) throws Exception {
        MemberVO vo = dao.getWithPassword(mid, mpw);
        MemberDTO memberDTO = modelMapper.map(vo, MemberDTO.class);

        return memberDTO;
    }
}
