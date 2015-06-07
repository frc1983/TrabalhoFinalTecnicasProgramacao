package Business;

import Exception.UsuarioException;
import Domain.Usuario;

public class UsuarioBusiness {
    public static void validaUsuario(Usuario usuario) throws UsuarioException{
        
        if (usuario.getNome() == null || usuario.getNome().length() == 0 || usuario.getNome().equals("")) {
            throw new UsuarioException("Nome inválido");
        }
        
        String regex_cpfCnpj = "^(?:\\d{11}|\\d{14})$";
        if (usuario.getCpfCnpj() == null || !usuario.getCpfCnpj().matches(regex_cpfCnpj)) {
            throw new UsuarioException("Cpf/Cnpj inválido");
        }
        
        String regex_email = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$";
        if (usuario.getEmail() == null || !usuario.getEmail().matches(regex_email)) {
            throw new UsuarioException("Email inválido");
        }
    }
}
