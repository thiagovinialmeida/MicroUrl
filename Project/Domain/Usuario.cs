namespace Project.Domain
{
    public class Usuario
    {
        public Guid Id { get; set; }
        public string Nome { get; set; }
        public string Email { get; set; }
        public string Senha { get; set; }
        public byte SaltSenha { get; set; }
        public List<Url> Urls { get; set; } = new List<Url> { };

        public Usuario(string nome, string email, string senha)
        {
            Id = Guid.NewGuid();
            Nome = nome;
            Email = email;
            Senha = senha;
        }

        public void AdicionarUrl(Url obj)
        {
            Urls.Add(obj);
        }
    }
}
