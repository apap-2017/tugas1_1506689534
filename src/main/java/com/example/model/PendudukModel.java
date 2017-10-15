package com.example.model;

import java.util.List;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.model.KeluargaModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendudukModel
{
    private String id;
    private String nik;
    private String nama;
    private String tempat_lahir;
    private String tanggal_lahir;
    private String jenis_kelamin;
    private String is_wni;
    private String id_keluarga;
    private String agama;
    private String pekerjaan;
    private String status_perkawinan;
    private String status_dalam_keluarga;
    private String golongan_darah;
    private String is_wafat;
     
}
