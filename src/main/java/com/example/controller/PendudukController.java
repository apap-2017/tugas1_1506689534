package com.example.controller;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    
    //FITUR1

    @RequestMapping("/penduduk")
    public String viewPenduduk (Model model, @RequestParam(value="nik", required = false) String nik)
    {
    	PendudukModel penduduk = pendudukDAO.selectPenduduk (nik);
    	
    	if (penduduk != null) {
	    	KeluargaModel keluarga = keluargaDAO.selectKeluarga(penduduk.getId_keluarga());
	    	KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(keluarga.getId_kelurahan());
	    	KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(kelurahan.getId_kecamatan());
	    	KotaModel kota = kotaDAO.selectKota(kecamatan.getId_kota());
	    	
	    	String tanggalLahir = penduduk.getTanggal_lahir();
	    	String[] arrTglLahir = tanggalLahir.split("-");
	    	String bulan = arrTglLahir[1].toString();
	    	   System.out.println(bulan);
	    	   switch (bulan) {
	    	   case "01":
	    	    bulan = "Januari";
	    	    break;
	    	   case "02":
	    	    bulan = "Februari"; 
	    	    break;
	    	   case "03":
	    	    bulan = "Maret"; 
	    	    break;
	    	   case "04":
	    	    bulan = "April"; 
	    	    break;
	    	   case "05":
	    	    bulan = "Mei"; 
	    	    break;
	    	   case "06":
	    	    bulan = "Juni";
	    	    break;
	    	   case "07": 
	    	    bulan = "Juli"; 
	    	    break;
	    	   case "08":
	    	    bulan = "Agustus"; 
	    	    break;
	    	   case "09":
	    	    bulan = "September"; 
	    	    break;
	    	   case "10":
	    	    bulan = "Oktober";
	    	    break;
	    	   case "11":
	    	    bulan = "November"; 
	    	    break;
	    	   case "12":
	    	    bulan = "Desember"; 
	    	    break;
	    	   default:
	    	    break;
	    	   }
	    	   tanggalLahir = arrTglLahir[2] + " " + bulan + " " + arrTglLahir[0];
	    	   penduduk.setTanggal_lahir(tanggalLahir);
	    
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
	    
	    	model.addAttribute("penduduk", penduduk);
	    	model.addAttribute("keluarga", keluarga);
	    	model.addAttribute("kelurahan", kelurahan);
	    	model.addAttribute("kecamatan", kecamatan);
	    	model.addAttribute("kota", kota);
	    	
	    	return "view";
    	}else {
    		model.addAttribute("nik", nik);
            return "not-found-penduduk";
    	}
    }
    
    
    //FITUR3
    
    @RequestMapping(value="/penduduk/tambah", method=RequestMethod.GET)
    public String tambahPendudukBaru(Model model) {
    	PendudukModel penduduk = new PendudukModel();
    	
    	penduduk.setJenis_kelamin("");
    	penduduk.setGolongan_darah("");
    	penduduk.setStatus_perkawinan("");
    	penduduk.setIs_wni("");
    	penduduk.setIs_wafat("");
    	model.addAttribute("penduduk", penduduk);
    	return "form-add";
    }
    
    @RequestMapping(value="/penduduk/tambah", method=RequestMethod.POST)
    public String tambahPenduduk(@Valid @ModelAttribute PendudukModel penduduk, Model model) {
    
    	KeluargaModel keluarga = keluargaDAO.selectKeluarga(penduduk.getId_keluarga());
    	KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(keluarga.getId_kelurahan());
    	KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(kelurahan.getId_kecamatan());
    	
    	//mengambil tanggal lahir penduduk yang dimasukkan dan dimasukkan ke dalam array dan pemisahnya adalah tanda -
    	String[] tanggalLahir = penduduk.getTanggal_lahir().split("-");
    	//jika  jenis kelamin penduduk adalah perempuan maka akan diambil array dengan indeks ke 2 dari tanggal lahirnya yaitu hari
    	//hari tanggal lahir penduduk perempuan akan ditambah 40
    	if(penduduk.getJenis_kelamin().equals("1")) {
    		tanggalLahir[2] = (Integer.parseInt(tanggalLahir[2]) +40) + "";	
    	}
    	//membuat nik baru dengan mengambil kode kecamatan dari indeks ke 0-6 ditambahkan dengan (hari + bulan + tahun)
    	//data tahun yang akan diambil hanya dari indeks ke 2 
    	String nik = kecamatan.getKode_kecamatan().substring(0,6) + tanggalLahir[2] + tanggalLahir[1] + tanggalLahir[0].substring(2);
    	//memanggil method selectNIK yang akan memunculkan data dengan nik yang sama karena apabila penduduk lahir
    	//pada tanggal yang sama maka harus dibuat nomor urutannya
    	List<PendudukModel> sama = pendudukDAO.selectNIK(nik + "%");
    	//nomor urutan dimulai dari 0001
    	String nomor = "0001";
    	if(sama.size() > 0) {
    		int nomorSama = Integer.parseInt(sama.get(sama.size()-1).getNik().substring(12)) + 1;
    		nomor = nomorSama + "";
    	}
    	
    	int count = 4 - nomor.length();
    	for(int i = 0; i < count; i++) {
    		nomor = "0" + nomor;
    	}
    	//nik baru akan ditambahkan dengan nomor urutan 
    	nik = nik + nomor;
    	//penduduk akan di set niknya 
    	penduduk.setNik(nik);
    	//mengambil semua daftar penduduk
    	List<PendudukModel> daftarPenduduk = pendudukDAO.selectAllPenduduk();
    	//penduduk yang baru id penduduknya akan ditambahkan 1 dari banyaknya penduduk saat ini
    	penduduk.setId("" + (daftarPenduduk.size() + 1));
    	//memanggil method tambah penduduk untuk menambahkan data-data penduduk lainnya ke database
    	pendudukDAO.addPenduduk(penduduk);
    	model.addAttribute("confirmation", "Penduduk dengan NIK " + nik + " berhasil ditambahkan" );
    	return "view-tambah";
    }
    
    // FITUR5
     
    @RequestMapping(value="/penduduk/ubah/{nik}", method=RequestMethod.GET)
    public String ubahPenduduk(@PathVariable(value = "nik")String nik, Model model) {
    	PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
    	
    	if (penduduk != null) {
    		model.addAttribute("penduduk", penduduk);
    	}else {
    		model.addAttribute("nik", "nik");
    		return "not-found";
    	}
    	return "form-update-penduduk";
    }
    
    @RequestMapping(value="/penduduk/ubah/{nik}", method=RequestMethod.POST)
    public String ubah(@PathVariable(value = "nik")String nik, @Valid @ModelAttribute PendudukModel pendudukUpdate, Model model) {
    	PendudukModel pendudukDb = pendudukDAO.selectPenduduk(nik);
    	//untuk menyimpan nomor nik yang lama
    	String nikLama = nik;
		pendudukDb.setNama(pendudukUpdate.getNama());
		pendudukDb.setTempat_lahir(pendudukUpdate.getTempat_lahir());
		pendudukDb.setTanggal_lahir(pendudukUpdate.getTanggal_lahir());
		pendudukDb.setJenis_kelamin(pendudukUpdate.getJenis_kelamin());
		pendudukDb.setIs_wni(pendudukUpdate.getIs_wni());
		pendudukDb.setId_keluarga(pendudukUpdate.getId_keluarga());
		pendudukDb.setAgama(pendudukUpdate.getAgama());
		pendudukDb.setPekerjaan(pendudukUpdate.getPekerjaan());
		pendudukDb.setStatus_perkawinan(pendudukUpdate.getStatus_perkawinan());
		pendudukDb.setStatus_dalam_keluarga(pendudukUpdate.getStatus_dalam_keluarga());
		pendudukDb.setGolongan_darah(pendudukUpdate.getGolongan_darah());

		KeluargaModel keluarga = keluargaDAO.selectKeluarga(pendudukDb.getId_keluarga());
		KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(keluarga.getId_kelurahan());
		KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(kelurahan.getId_kecamatan());

		String[] tanggalLahir = pendudukDb.getTanggal_lahir().split("-");
		if(pendudukDb.getJenis_kelamin().equals("1")) {
			tanggalLahir[2] = (Integer.parseInt(tanggalLahir[2]) +40) + "";

		}
		String newNik = kecamatan.getKode_kecamatan().substring(0,6) + tanggalLahir[2] + tanggalLahir[1] + tanggalLahir[0].substring(2);
		List<PendudukModel> sama = pendudukDAO.selectNIK(newNik + "%");
		String nomor = "0001";
		if(sama.size() > 0) {
			int nomorSama = Integer.parseInt(sama.get(sama.size()-1).getNik().substring(12)) + 1;
			nomor = nomorSama + "";
		}

		int count = 4 - nomor.length();
		for(int i = 0; i < count; i++) {
			nomor = "0" + nomor;
		}
		newNik = newNik + nomor;
		pendudukDb.setNik(newNik);

		pendudukDAO.updatePenduduk(pendudukDb);
		model.addAttribute("confirmation", "Penduduk dengan NIK " + nikLama + " berhasil diubah" );
    	return "view-tambah";
    	
    }
    
    // FITUR7
    
    @RequestMapping(value = "/penduduk/mati", method=RequestMethod.POST)
    public String pendudukWafat(Model model, @RequestParam(value="nik") String nik) {
		PendudukModel pendudukModel = pendudukDAO.selectPenduduk(nik);
		//menyimpan nik penduduk
		String nikMati = nik;
		if(pendudukModel != null) {
			//mengambil data semua penduduk yang termasuk ke dalam anggota suatu keluarga
			List<PendudukModel> pendudukModels = pendudukDAO.selectAnggotaKeluarga(pendudukModel.getId_keluarga());
			int counter = 1;
			for(PendudukModel x : pendudukModels) {
				if(x.getIs_wafat().equals("1")) {
					counter++;
				}
			}
			if(counter >= pendudukModels.size()) {
				keluargaDAO.updateIsTidakBerlaku("1",pendudukModel.getId_keluarga());
			}
			pendudukDAO.updateIsWafat("1",pendudukModel.getId());
			model.addAttribute("confirmation", "Penduduk dengan NIK " + nikMati + " sudah tidak aktif");
			return "view-tambah" ;
		} else{
			model.addAttribute("nik", nik);
			return "not-found-penduduk";
		}
	}
    
    
    // FITUR2
    
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
    
    // FITUR4
   
    @RequestMapping(value="/keluarga/tambah", method=RequestMethod.GET)
    public String tambahKeluargaBaru(Model model){
    	 KeluargaModel keluarga = new KeluargaModel();
         keluarga.setId_kelurahan("");
         model.addAttribute("keluarga", keluarga);
         //mengambil semua daftar kelurahan
         List<KelurahanModel> kelurahan = kelurahanDAO.selectAllKelurahan();
         //mengambil semua daftar kecamatan
         List<KecamatanModel> kecamatan = kecamatanDAO.selectAllKecamatan();
         //mengambil semua daftar kota
         List<KotaModel> kota = kotaDAO.selectAllKota();
         
         //melakukan looping untuk mendapatkan kelurahan yang id kecamatannya sama dengan id kecamatan yang tersedia
         //mendapatkan kecamatan yang id kotanya sama dengan id kota yang tersedia
         for(int i = 0; i < kelurahan.size(); i++){
             for(int j = 0; j < kecamatan.size(); j++){
                 for(int k = 0 ; k < kota.size(); k++){
                     if(kelurahan.get(i).getId_kecamatan().equals(kecamatan.get(j).getId())){
                         if(kecamatan.get(j).getId_kota().equals(kota.get(k).getId())){
                        	 //Hasilnya akan disimpan di dalam variabel namaKelurahan
                             String namaKelurahan = kota.get(k).getNama_kota()+ " / " + kecamatan.get(j).getNama_kecamatan() +
                             " / " + kelurahan.get(i).getNama_kelurahan();
                             kelurahan.get(i).setNama_kelurahan(namaKelurahan);
                         }
                     }
                 }
             }
         }
         model.addAttribute("kelurahan", kelurahan);
         return "form-add-keluarga";
    }
    
    @RequestMapping(value="/keluarga/tambah", method=RequestMethod.POST)
    public String tambahKeluarga(@Valid @ModelAttribute KeluargaModel keluarga, Model model) {
    	//untuk mengenerate nomor nkk baru maka harus diatur tanggal KK diterbitkan
    	//dengan method new Date() dimana tanggal akan diformat dalam tahun-bulan-hari
    	Date sekarang = new Date();
    	SimpleDateFormat tanggal = new SimpleDateFormat("yyyy-MM-dd");
    	String tanggalSekarang = tanggal.format(sekarang);
    	String[] date = tanggalSekarang.split("-");
    	//mengambil id kelurahan dari data keluarga
    	KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(keluarga.getId_kelurahan());
    	//nomor kk dibentuk dari kode kelurahan yang diambil dari indeks 0-6 ditambah dengan hari
    	//bulan dan tahun yang diambil datanya hanya mulai dari indeks ke 2
    	String nomor_kk = kelurahan.getKode_kelurahan().substring(0,6);
    	nomor_kk += date[2] + date[1] + date [0].substring(2);
    	//memanggil method selectNKK yang akan memunculkan data dengan nkk yang sama karena apabila
    	// penduduk membuat KK pada tanggal yang sama maka harus dibuat nomor urutannya
    	List<KeluargaModel> sama = keluargaDAO.selectNKK(nomor_kk + "%");
    	//nomor urutan dimulai dari 0001
    	String nomor = "0001";
    	if(sama.size() > 0) {
    		int nomorSama = Integer.parseInt(sama.get(sama.size()-1).getNomor_kk().substring(12)) + 1;
    		nomor = nomorSama + "";
    	}

    	int count = 4 - nomor.length();
    	for(int i = 0; i < count; i++) {
    		nomor = "0" + nomor;
    	}
    	//nomor kk akan ditambahkan nomor urutan
    	nomor_kk = nomor_kk + nomor;
    	keluarga.setNomor_kk(nomor_kk);
    	keluarga.setIs_tidak_berlaku("0");
    	//mengambil semua daftar keluarga
    	List<KeluargaModel> daftarKeluarga = keluargaDAO.selectAllKeluarga();
    	//id keluarga ditambahkan 1 dari jumlah keluarga saat ini
    	keluarga.setId("" +(daftarKeluarga.size() +1));
    	//memanggil method addKeluarga untuk menambahkan data-data keluarga pada database
    	keluargaDAO.addKeluarga(keluarga);
    	model.addAttribute("confirmation", "Penduduk dengan NKK " + nomor_kk + " berhasil ditambahkan" );
    	return "view-tambah";
    	
    }
    
    // FITUR6
    @RequestMapping(value="/keluarga/ubah/{nkk}", method = RequestMethod.GET)
    public String ubahKeluarga(@PathVariable(value = "nkk") String nkk, Model model) {
        KeluargaModel keluargaModel = keluargaDAO.selectKeluargaNKK(nkk);
        if(keluargaModel != null) {
            model.addAttribute("nkk", nkk);
            model.addAttribute("keluarga",keluargaModel);
            //mengambil semua daftar kelurahan
            List<KelurahanModel> kelurahan = kelurahanDAO.selectAllKelurahan();
            //mengambil semua daftar kecamatam
            List<KecamatanModel> kecamatan = kecamatanDAO.selectAllKecamatan();
            //mengambil semua daftar kota
            List<KotaModel> kota = kotaDAO.selectAllKota();
            
          //melakukan looping untuk mendapatkan kelurahan yang id kecamatannya sama dengan id kecamatan yang tersedia
          //mendapatkan kecamatan yang id kotanya sama dengan id kota yang tersedia
            for(int i = 0; i < kelurahan.size(); i++){
                for(int j = 0; j < kecamatan.size(); j++){
                    for(int k = 0 ; k < kota.size(); k++){
                        if(kelurahan.get(i).getId_kecamatan().equals(kecamatan.get(j).getId())){
                            if(kecamatan.get(j).getId_kota().equals(kota.get(k).getId())){
                                String namaKelurahan = kota.get(k).getNama_kota()+ " / " + kecamatan.get(j).getNama_kecamatan() +
                                " / " + kelurahan.get(i).getNama_kelurahan();
                                kelurahan.get(i).setNama_kelurahan(namaKelurahan);
                                if(kelurahan.get(i).getId().equals(keluargaModel.getId_kelurahan())) {
                                    model.addAttribute("current_kelurahan", kelurahan.get(i));
                                }
                            }
                        }
                    }
                }
            }

            model.addAttribute("kelurahan", kelurahan);
            //jika nkk ditemukan mengembalikan ke halaman form-update
            return "form-update";
        }
        else {
        	//jika nkk tidak ditemukan maka akan ditampilkan pesan nkk tidak ditemukan
        	model.addAttribute("nomor_kk", nkk);
            return "not-found";
        }
    }

    @RequestMapping(value="/keluarga/ubah/{nkk}", method = RequestMethod.POST)
    public String ubahKeluargaku(@PathVariable(value = "nkk") String nkk
            ,@Valid @ModelAttribute KeluargaModel keluargaUpdate, Model model) {
    	//mengambil keluarga yang sesuai nkk
        KeluargaModel keluargaDb = keluargaDAO.selectKeluargaNKK(nkk);
        //menyimpan nilai nkk lama yang belum berubah
        String nkkLama = nkk;
        //mengubah data menjadi data yang baru
        keluargaDb.setAlamat(keluargaUpdate.getAlamat());
        keluargaDb.setRT(keluargaUpdate.getRT());
        keluargaDb.setRW(keluargaUpdate.getRW());
        keluargaDb.setIs_tidak_berlaku(keluargaUpdate.getIs_tidak_berlaku());

        //jika ganti id kelurahan
        if(!keluargaUpdate.getId_kelurahan().equals(keluargaDb.getId_kelurahan())) {
            Date sekarang = new Date();
            SimpleDateFormat tanggal = new SimpleDateFormat("yyyy-MM-dd");
            String tanggalSekarang = tanggal.format(sekarang);
            String[] date = tanggalSekarang.split("-");
            KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(keluargaUpdate.getId_kelurahan());
            String nomor_kk = kelurahan.getKode_kelurahan().substring(0,6);
            nomor_kk += date[2] + date[1] + date [0].substring(2);

            List<KeluargaModel> sama = keluargaDAO.selectNKK(nomor_kk + "%");
            String nomor = "0001";
            if(sama.size() > 0) {
                int nomorSama = Integer.parseInt(sama.get(sama.size()-1).getNomor_kk().substring(12)) + 1;
                nomor = nomorSama + "";
            }

            int count = 4 - nomor.length();
            for(int i = 0; i < count; i++) {
                nomor = "0" + nomor;
            }
            nomor_kk = nomor_kk + nomor;
            keluargaDb.setNomor_kk(nomor_kk);
        }

        keluargaDb.setId_kelurahan(keluargaUpdate.getId_kelurahan());
        //memanggil method update pada KeluargaService
        keluargaDAO.update(keluargaDb);
    	model.addAttribute("confirmation", "Penduduk dengan NKK " + nkkLama + " berhasil diubah" );
        return "view-tambah";
    }
    
    
    // FITUR8
    @RequestMapping("/penduduk/cari")
	private String cariPenduduk(Model model, 
			@RequestParam(value = "id_kota", required = false) String id_kota,
			@RequestParam(value = "id_kecamatan", required = false) String id_kecamatan,
			@RequestParam(value = "id_kelurahan", required = false) String id_kelurahan
			){
			//mengambil seluruh daftar kota 
    		List <KotaModel> kota = kotaDAO.selectAllKota();
			model.addAttribute("kota", kota);
			if (id_kota != null)
			{
				//mengambil daftar kecamatan berdasarkan id kota
				List <KecamatanModel> kecamatan = kecamatanDAO.selectKecamatanByKota(id_kota);
				model.addAttribute("id_kota", id_kota);
				model.addAttribute("kecamatan", kecamatan);
			}
			else
			{
				model.addAttribute("id_kota", null);
			}
			if (id_kecamatan != null)
			{	
				//mengambil daftar kelurahan berdarkan id kecamatan 
				List <KelurahanModel> kelurahan = kelurahanDAO.selectKelurahanByKecamatan(id_kecamatan);
				model.addAttribute("kelurahan", kelurahan);
				model.addAttribute("id_kecamatan", id_kecamatan);	
			}
			else
			{
				model.addAttribute("id_kecamatan", id_kecamatan);
			}
			
			if (id_kota != null && id_kecamatan != null && id_kelurahan != null)
			{
				//mengambil daftar penduduk berdasarkan kelurahan
				List <PendudukModel> penduduk = pendudukDAO.selectPendudukByKelurahan(id_kelurahan);
				model.addAttribute("penduduk", penduduk);
				return "sukses-cari";
			}
		return "cari-penduduk";
	}
}