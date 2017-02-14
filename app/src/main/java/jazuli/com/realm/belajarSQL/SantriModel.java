package jazuli.com.realm.belajarSQL;

/**
 * Created by jazuli on 13/02/17.
 */

public class SantriModel {
    public String getId_santri() {
        return id_santri;
    }

    public void setId_santri(String id_santri) {
        this.id_santri = id_santri;
    }

    public String getNama_santri() {
        return nama_santri;
    }

    public void setNama_santri(String nama_santri) {
        this.nama_santri = nama_santri;
    }

    public String getAsal_santri() {
        return asal_santri;
    }

    public void setAsal_santri(String asal_santri) {
        this.asal_santri = asal_santri;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getSkill_santri() {
        return skill_santri;
    }

    public void setSkill_santri(String skill_santri) {
        this.skill_santri = skill_santri;
    }

    private String id_santri = "id_santri";
    private String nama_santri = "nama_santri";
    private String asal_santri = "asal_santri";
    private String tgl_lahir = "tgl_lahir";
    private String skill_santri = "skill_santri";
}
