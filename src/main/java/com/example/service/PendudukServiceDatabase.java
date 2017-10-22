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
	public List<PendudukModel> selectAllPenduduk() {
		return pendudukMapper.selectAllPenduduk();
	}

	@Override
	public List<PendudukModel> selectAnggotaKeluarga(String id) {
		return pendudukMapper.selectAnggotaKeluarga(id);
	}

	@Override
	public List<PendudukModel> selectNIK(String nik) {
		return pendudukMapper.selectNIK(nik);
	}

	@Override
	public PendudukModel selectPenduduk(String nik) {
		return pendudukMapper.selectPenduduk(nik);
	}

	@Override
	public void addPenduduk(PendudukModel penduduk) {
		pendudukMapper.addPenduduk(penduduk);
	}

	@Override
	public void updatePenduduk(PendudukModel penduduk) {
		pendudukMapper.updatePenduduk(penduduk);
		
	}

	@Override
	public void updateIsWafat(String is_wafat, String id) {
		pendudukMapper.updateIsWafat(is_wafat, id);
		
	}

	@Override
	public List<PendudukModel> selectPendudukByKelurahan(String id_kelurahan) {
		return pendudukMapper.selectPendudukByKelurahan(id_kelurahan);
	}

}
