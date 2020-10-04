import java.util.ArrayList;

public class Tuite<T> {

    private final Usuario autor;
    private final String texto;
    private T anexo;


    // hashtags?
    // objeto anexado?

    public Tuite(Usuario autor, String texto) {
        this.autor = autor;
        this.texto = texto;

    }

    public void anexarAlgo(T anexo) {
        this.anexo = anexo;
    }

    public Object getAnexo() {
        return this.anexo;
    }

    public Usuario getAutor() {
        return this.autor;
    }

    public String getTexto() {
        return this.texto;
    }

    public void adicionarHashtagJaExistente(String hashtag){
        for (String procuraHashtag : TuiterLite.hashtags){
            if(procuraHashtag.equals(hashtag)){
                TuiterLite.quantHashtags.set(TuiterLite.hashtags.indexOf(hashtag),
                        TuiterLite.quantHashtags.get(TuiterLite.hashtags.indexOf(hashtag))+1);
                break;
            }
        }
    }
    public void adicionarHashtagNaoExistente(String hashtag){
        TuiterLite.hashtags.add(hashtag);
        TuiterLite.quantHashtags.add(1);
    }

    public ArrayList<String> getHashtags() {
        return TuiterLite.hashtags;
    }
}
