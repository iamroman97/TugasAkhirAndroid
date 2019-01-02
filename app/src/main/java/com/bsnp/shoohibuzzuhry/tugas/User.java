package com.bsnp.shoohibuzzuhry.tugas;

public class User
{
    private String judul;
    private int kategori;
    private String isi;

    public User(){
    }
    public User(String judul, int kategori, String isi) {
        this.judul = judul;
        this.kategori = kategori;
        this.isi = isi;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getJudul() {
        return judul;
    }

    public void setKategori(int kategori) {
        this.kategori = kategori;
    }

    public int getKategori() {
        return kategori;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getIsi() {
        return isi;
    }

}