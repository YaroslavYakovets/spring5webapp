 package guru.springframework.spring5webapp.bootstrap;

 import guru.springframework.spring5webapp.domain.Author;
 import guru.springframework.spring5webapp.domain.Book;
 import guru.springframework.spring5webapp.domain.Publisher;
 import guru.springframework.spring5webapp.repository.AuthorRepository;
 import guru.springframework.spring5webapp.repository.BookRepository;
 import guru.springframework.spring5webapp.repository.PublisherRepository;
 import org.springframework.boot.CommandLineRunner;
 import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }


    @Override
    public void run(String... args) throws Exception
    {
        Publisher publisher = new Publisher();
        publisher.setName("SFG publisher");
        publisher.setAddressLine1("One drive");
        publisher.setCity("St.Petersburg");
        publisher.setState("FL");
        publisherRepository.save(publisher);

        Author ericEvans = new Author("Eric", "Evans");
        Book ddd = new Book("Data Driven Design", "1234567");
        ericEvans.getBooks().add(ddd);
        ddd.getAuthors().add(ericEvans);

        Author rodJohnson = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "8689216");
        rodJohnson.getBooks().add(noEJB);
        noEJB.getAuthors().add(rodJohnson);

        ddd.setPublisher(publisher);
        noEJB.setPublisher(publisher);


        authorRepository.save(ericEvans);
        authorRepository.save(rodJohnson);

        bookRepository.save(ddd);
        bookRepository.save(noEJB);


        publisher.getBooks().add(ddd);
        publisher.getBooks().add(noEJB);
        publisherRepository.save(publisher);

        System.out.println("Started in bootstrap");
        System.out.println("Publishers count = " + publisherRepository.count());
        System.out.println("Authors count = " + authorRepository.count());
        System.out.println("Books count = " + bookRepository.count());
        System.out.println("Books assigned to publisher " + publisher.getName()+ " : " + publisher.getBooks().size());

    }
}
