import java.util.ArrayList;

/**
 *  Esta classe implementa um sistema de mensagens curtas estilo Twitter.
 *  É preciso cadastrar um usuário, identificado pelo seu e-mail, para que tuítes possam ser feitos.
 *  Usuários começam como iniciantes, depois são promovidos a senior e a ninja, em função do número de tuítes.
 *  Existe um tamanho máximo permitido por mensagem (constante TAMANHO_MAXIMO_TUITES).
 *  As mensagens podem conter hashtags (palavras iniciadas por #), que são detectadas automaticamente.
 *  Os tuítes podem conter, além da mensagem de texto, um anexo qualquer.
 *  Há um método para retornar, a qualquer momento, qual a hashtag mais usada em toda a história do sistema.
 */
public class TuiterLite<T> {

    public static final int TAMANHO_MAXIMO_TUITES = 120;
    private ArrayList<Usuario> usuarios;
    private boolean ExisteNoArrayList;
    public static ArrayList<String> hashtags;
    public static ArrayList<Integer> quantHashtags;
    public static String hashtagComum;

    public TuiterLite(){
        this.usuarios = new ArrayList<Usuario>();
        this.hashtags = new ArrayList<String>();
        this.quantHashtags = new ArrayList<Integer>();
    }

    /**
     * Cadastra um usuário, retornando o novo objeto Usuario criado.
     * Se o email informado já estiver em uso, não faz nada e retorna null.
     * @param nome O nome do usuário.
     * @param email O e-mail do usuário (precisa ser único no sistema).
     * @return O Usuario criado.
     */
    public Usuario cadastrarUsuario(String nome, String email) {
        Usuario novoUsuario = new Usuario(nome, email);
        if(this.usuarios.size() == 0){
            this.usuarios.add(novoUsuario);
        }else{
            for (Usuario usuario : this.usuarios) {
                if (usuario.getEmail().equals(novoUsuario.getEmail())) {
                    return null;
                }
            }
        }
        // toDo: armazenar esse usuário em alguma estrutura que seja atributo de TuiterLite
        return novoUsuario;
    }

    /**
     * Tuíta algo, retornando o objeto Tuíte criado.
     * Se o tamanho do texto exceder o limite pré-definido, não faz nada e retorna null.
     * Se o usuário não estiver cadastrado, não faz nada e retorna null.
     *
     * @param usuario O autor do tuíte
     * @param texto O texto desejado
     * @return Um "tuíte", que será devidamente publicado no sistema
     */
    public Tuite tuitarAlgo(Usuario usuario, String texto) {
        for (Usuario value : this.usuarios) {
            if (!(value.equals(usuario))) {
                this.ExisteNoArrayList = false;
                break;
            }
            this.ExisteNoArrayList = true;
        }
            if(texto.length() <= TAMANHO_MAXIMO_TUITES && ExisteNoArrayList){
                Tuite tuite = new Tuite(usuario,texto);
                usuario.setTuites(usuario.getTuites()+1);
                if(usuario.getTuites() >= usuario.MIN_TUITES_SENIOR &&
                        usuario.getTuites() < usuario.MIN_TUITES_NINJA){ usuario.setNivel(NivelUsuario.SENIOR);}
                if(usuario.getTuites() >= usuario.MIN_TUITES_NINJA){
                    usuario.setNivel(NivelUsuario.NINJA);
                }
                VerificandoAHashtagMaisComum(tuite);
                return tuite;
            } else {
                return null;
            }
    }

    /**
     * Retorna a hashtag mais comum dentre todas as que já apareceram.
     * A cada tuíte criado, hashtags devem ser detectadas automaticamente para que este método possa funcionar.
     * @return A hashtag mais comum, ou null se nunca uma hashtag houver sido tuitada.
     */

    public void VerificandoAHashtagMaisComum(Tuite tuite){
        String[] TuitePostado = tuite.getTexto().split("[\\s]");
        for(String PalavraEscolhida : TuitePostado){
            if(PalavraEscolhida.startsWith("#")){
                for(String hashtagArmazenada : hashtags){
                    if(PalavraEscolhida.equals(hashtagArmazenada)){
                        tuite.adicionarHashtagJaExistente(PalavraEscolhida);
                        break;
                    }
                }
                tuite.adicionarHashtagNaoExistente(PalavraEscolhida);
            }
        }
        setHashtagMasComum();
    }

    public String getHashtagMaisComum() {
        return hashtagComum;
    }

    public void setHashtagMasComum(){
        if(quantHashtags.size() > 0){
            int quantHashMaisComum = 0;
            int LocalHashtagMaisComum = 0;
            for(Integer hash: quantHashtags){
                if(hash > quantHashtags.get(quantHashMaisComum)){
                    quantHashMaisComum = hash;
                    LocalHashtagMaisComum = quantHashtags.indexOf(quantHashMaisComum);
                }
            }
            hashtagComum = hashtags.get(LocalHashtagMaisComum);
        }
    }


    // Mainzinho bobo, apenas ilustrando String.split(regexp), e o String.startsWith()

    public static void main(String[] args) {
        String frase = "Testando algo,sdf com #hashtags no meio #teste vamos ver!fdfgf";
        String[] palavras = frase.split("[\\s,!]");
        for (String palavra : palavras) {
            if (palavra.startsWith("#")) {
                System.out.println(palavra);
            }
        }

        // Ilustrando o uso de um StringBuilder (ou StringBuffer)
        StringBuffer sb = new StringBuffer();
        sb.append("Oi,");
        sb.append(" tudo bem?");
        sb.append("0").append("1").append(2).append("3");
        String resultadoDasConcatenacoes = sb.toString();
        System.out.println(resultadoDasConcatenacoes);

        /* equivalentemente (mas bem menos performático,
              porque cria novas Strings a cada concatenação) */
        String minhaString;
        minhaString = "Oi,";
        minhaString += " tudo bem?";
        minhaString += "0";
        minhaString += "1";
        minhaString += "2";
        minhaString += "3";
        System.out.println(minhaString);

    }
}
