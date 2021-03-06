import java.awt.*;

public class Usuario {

    public static final int MIN_TUITES_SENIOR = 200;
    public static final int MIN_TUITES_NINJA = 1000;

    private final String email;
    private String nome;
    private Image foto;
    private int tuites;

    // Pode ser INICIANTE, SENIOR ou NINJA
    private NivelUsuario nivel;

    public Usuario(String nome, String email) {
        this.email = email;
        this.nome = nome;
        this.nivel = NivelUsuario.INICIANTE;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public Image getFoto() {
        return this.foto;
    }

    public String getEmail() {
        return this.email;
    }

    public String getNome() {
        return this.nome;
    }

    public NivelUsuario getNivel() {
        return nivel;
    }

    public void setNivel(NivelUsuario nivel){ this.nivel = nivel; }

    public void setTuites(int tuites){ this.tuites = tuites; }

    public int getTuites(){ return this.tuites; }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return (usuario.getEmail().equals(this.getEmail()));
    }
}
