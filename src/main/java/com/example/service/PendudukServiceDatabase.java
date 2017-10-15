package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
import com.example.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PendudukServiceDatabase implements PendudukService
{
    @Autowired
    private PendudukMapper pendudukMapper;

	@Override
	public PendudukModel selectPenduduk(String nik) {
		log.info("select penduduk with nik {}", nik);
		return pendudukMapper.selectPenduduk(nik);
	}

	@Override
	public List<PendudukModel> selectAnggotaKeluarga(String id) {
		log.info("select penduduk with id {}", id);
		return pendudukMapper.selectAnggotaKeluarga(id);
	}

}
