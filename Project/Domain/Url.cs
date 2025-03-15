namespace Project.Domain
{
    public class Url
    {
        public int Id { get; set; }
        public string LongUrl { get; set; }
        public string ShortUrl {  get; set; }
    
        public Url(int id, string longUrl, string shortUrl)
        {
            Id = id;
            LongUrl = longUrl;
            ShortUrl = shortUrl;
        }
    }
}
