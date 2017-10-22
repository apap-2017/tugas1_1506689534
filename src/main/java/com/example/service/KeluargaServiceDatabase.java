package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
import com.example.model.KeluargaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KeluargaServiceDatabase implements KeluargaService
{
	@Autowired
    private PendudukMapper pendudukMapper;

	@Override
	public KeluargaModel selectKeluarga(String id_keluarga) {
		log.info("select keluarga with id_keluarga {}", id_keluarga);
		return pendudukMapper.selectKeluarga(id_keluarga);
	}

	@Override
	public KeluargaModel selectKeluargaNKK(String nomor_kk) {
		log.info("select keluarga with nomor_kk {}", nomor_kk);
		return pendudukMapper.selectKeluargaNKK(nomor_kk);
	}

	@Override
	public List<KeluargaModel> selectAllKeluarga() {
		log.info("select semua keluarga {}");
		return pendudukMapper.selectAllKeluarga();
	}

	@Override
	public List<KeluargaModel> selectNKK(String nomor_kk) {
		return pendudukMapper.selectNKK(nomor_kk);
	}

	@Override
	public void addKeluarga(KeluargaModel keluarga) {
		pendudukMapper.addKeluarga(keluarga);
	}

	@Override
	public void updateIsTidakBerlaku(String is_tidak_berlaku, String id) {
		pendudukMapper.updateIsTidakBerlaku(is_tidak_berlaku, id);
	}

	@Override
	public void update(KeluargaModel keluargaModel) {
		pendudukMapper.update(keluargaModel);
		
	}

	
}
