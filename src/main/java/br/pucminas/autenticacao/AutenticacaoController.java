package br.pucminas.autenticacao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pucminas.autenticacao.dto.Grupo;
import br.pucminas.autenticacao.dto.Permissao;
import br.pucminas.autenticacao.dto.Token;
import br.pucminas.autenticacao.dto.Usuario;

@RestController
@RequestMapping("/v1/public/")
public class AutenticacaoController {

    private List<Usuario> usuarios = new ArrayList<>();
    private List<Grupo> grupos = new ArrayList<>();
    private List<Permissao> permissoes = new ArrayList<>();

    // Usuario
    @GetMapping("usuarios")
    public List<Usuario> todosUsuarios() {
        return usuarios;
    }

    @PostMapping("usuarios")
    public Usuario novoUsuario(@RequestBody Usuario usuario) {
        usuario.setId(new Long(new Random().nextInt(100)));
        usuarios.add(usuario);

        return usuario;
    }

    @PutMapping("usuarios/{id}")
    public Usuario autalizaUsuario(@RequestBody Usuario usuario, @PathVariable Long id) {
        usuarios.stream().filter(usuarioSelecionado -> usuarioSelecionado.getId().equals(id))
                .forEach(usuarioSelecionado -> {
                    usuarioSelecionado.setNome(usuario.getNome());
                    usuarioSelecionado.setLogin(usuario.getLogin());
                    usuarioSelecionado.setCpf(usuario.getCpf());
                });
        return usuario;
    }

    @PutMapping("usuarios/{login}/{senha}")
    public Token autenticarUsuario(@PathVariable String login, @PathVariable String senha) {

        Usuario usuarioLista = usuarios.stream()
                .filter(usuarioSelecionado -> usuarioSelecionado.getLogin().equals(login)).findFirst().orElse(null);
        Token token = null;

        if (null != usuarioLista) {
            if (credenciaisValidas(usuarioLista, senha)) {
                token = criaToken();
            }
            atualizaTokenUsuario(usuarioLista, token);
        }

        return token;
    }

    private boolean credenciaisValidas(Usuario usuario, String senha) {
        return usuario.getSenha().equals(usuario.getSenha());
    }

    private void atualizaTokenUsuario(Usuario usuario, Token token) {
        usuarios.stream().filter(usuarioSelecionado -> usuarioSelecionado.getId().equals(usuario.getId()))
                .forEach(usuarioSelecionado -> {
                    usuarioSelecionado.setToken(token);
                });

    }

    private Token criaToken() {

        Token token = new Token();
        DateTimeFormatter isoDateFormat = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime now = LocalDateTime.now();
        String dateFormat = now.format(isoDateFormat);

        token.setToken(Math.abs(new Random().nextLong()) + "");
        token.setDataValidade(dateFormat);
        return token;
    }

    @GetMapping("usuarios/{token}")
    public Usuario obterUsuarioPorToken(@PathVariable String token) {
        Usuario usuario = usuarios.stream().filter(usuarioSelecionado -> usuarioSelecionado.getToken().equals(token))
                .findFirst().orElse(null);
        return usuario;
    }

    @DeleteMapping("usuarios/{id}")
    void deleteUsuario(@PathVariable Long id) {
        usuarios.removeIf(usuarioSelecionado -> usuarioSelecionado.getId().equals(id));
    }

    // Grupo
    @GetMapping("grupos")
    public List<Grupo> todosGrupos() {
        return grupos;
    }

    @PostMapping("grupos")
    public Grupo novoGrupo(@RequestBody Grupo grupo) {
        grupo.setId(new Long(new Random().nextInt(100)));
        grupos.add(grupo);

        return grupo;
    }

    @PutMapping("grupos/{id}")
    public Grupo autalizaGrupo(@RequestBody Grupo grupo, @PathVariable Long id) {
        grupos.stream().filter(grupoSelecionado -> grupoSelecionado.getId().equals(id)).forEach(grupoSelecionado -> {
            grupoSelecionado.setNome(grupo.getNome());
        });
        return grupo;
    }

    @DeleteMapping("grupos/{id}")
    public void deleteGrupo(@PathVariable Long id) {
        grupos.removeIf(grupoSelecionado -> grupoSelecionado.getId().equals(id));
    }

    // Permissao
    @GetMapping("permissoes")
    public List<Permissao> todasPermissoes() {
        return permissoes;
    }

    @PostMapping("permissoes")
    public Permissao novaPermissao(@RequestBody Permissao permissao) {
        permissao.setId(new Long(new Random().nextInt(100)));
        permissoes.add(permissao);

        return permissao;
    }

    @PutMapping("permissoes/{id}")
    public Permissao autalizaPermissao(@RequestBody Permissao permissao, @PathVariable Long id) {
        permissoes.stream().filter(permissaoSelecionado -> permissaoSelecionado.getId().equals(id))
                .forEach(permissaoSelecionado -> {
                    permissaoSelecionado.setDescricao(permissao.getDescricao());
                });
        return permissao;
    }

    @DeleteMapping("permissoes/{id}")
    public void deletePermissao(@PathVariable Long id) {
        permissoes.removeIf(permissaoSelecionado -> permissaoSelecionado.getId().equals(id));
    }

}
