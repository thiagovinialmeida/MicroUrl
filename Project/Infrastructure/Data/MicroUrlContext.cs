using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Project.Domain;

    public class MicroUrlContext : DbContext
    {
        public MicroUrlContext (DbContextOptions<MicroUrlContext> options)
            : base(options)
        {
        }

        public DbSet<Usuario> Usuario { get; set; } = default!;
    }
