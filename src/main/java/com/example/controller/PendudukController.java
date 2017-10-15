package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.PendudukModel;
import com.example.model.KecamatanModel;
import com.example.model.KelurahanModel;
import com.example.model.KeluargaModel;
import com.example.model.KotaModel;
import com.example.service.PendudukService;
import com.example.service.KecamatanService;
import com.example.service.KelurahanService;
import com.example.service.KeluargaService;
import com.example.service.KotaService;

@Controller
public class PendudukController
{
    @Autowired
    PendudukService pendudukDAO;
    
    @Autowired
    KeluargaService keluargaDAO;
    
    @Autowired
    KecamatanService kecamatanDAO;
    
    @Autowired
    KelurahanService kelurahanDAO;
    
    @Autowired
    KotaService kotaDAO;

    @RequestMapping("/")
    public String index ()
    {
        return "index";
    }

    @RequestMapping("/penduduk")
    public String viewPenduduk (Model model, @RequestParam(value="nik", required = false) String nik)
    {
    	PendudukModel penduduk = pendudukDAO.selectPenduduk (nik);
    	
    	if (penduduk != null) {
	    	KeluargaModel keluarga = keluargaDAO.selectKeluarga(penduduk.getId_keluarga());
	    	KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(keluarga.getId_kelurahan());
	    	KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(kelurahan.getId_kecamatan());
	    	KotaModel kota = kotaDAO.selectKota(kecamatan.getId_kota());
	    
	    	if(penduduk.getIs_wni().equals("0")) {
	    		penduduk.setIs_wni("Bukan WNI");
	    	}else {
	    		penduduk.setIs_wni("WNI");
	    	}
	    	
	    	if(penduduk.getIs_wafat().equals("0")) {
	    		penduduk.setIs_wafat("Hidup");
	    	}else {
	    		penduduk.setIs_wafat("Wafat");
	    	}
	    	
	    	if(penduduk.getJenis_kelamin().equals("0")) {
	    		penduduk.setJenis_kelamin("Laki-laki");
	    	}else {
	    		penduduk.setJenis_kelamin("Perempuan");
	    	}
	    	
	    	model.addAttribute("penduduk", penduduk);
	    	model.addAttribute("keluarga", keluarga);
	    	model.addAttribute("kelurahan", kelurahan);
	    	model.addAttribute("kecamatan", kecamatan);
	    	model.addAttribute("kota", kota);
	    	
	    	return "view";
    	}else {
    		model.addAttribute("nik", nik);
            return "not-found";
    	}
    }
    
    @RequestMapping("/keluarga")
    public String viewPendudukNKK (Model model, @RequestParam(value="nomor_kk", required = false) String nomor_kk)
    {
    	KeluargaModel keluarga = keluargaDAO.selectKeluargaNKK(nomor_kk);
    	
    	if (keluarga != null) {
    		List<PendudukModel> penduduk = pendudukDAO.selectAnggotaKeluarga(keluarga.getId());
        	KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(keluarga.getId_kelurahan());
        	KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(kelurahan.getId_kecamatan());
        	KotaModel kota = kotaDAO.selectKota(kecamatan.getId_kota());
        	
        	model.addAttribute("penduduk", penduduk);
        	model.addAttribute("keluarga", keluarga);
	    	model.addAttribute("kelurahan", kelurahan);
	    	model.addAttribute("kecamatan", kecamatan);
	    	model.addAttribute("kota", kota);
	    	
	    	return "viewall";
    	}else {
    		model.addAttribute("nomor_kk", nomor_kk);
            return "not-found";
    	}
    }
 }



