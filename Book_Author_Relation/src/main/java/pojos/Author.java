package pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "authors")
public class Author extends BaseEntity{
	@Column(name = "first_name", length=20)
	private String firstName;
	@Column(name = "last_name", length=20)
	private String lastName;
	@Column(length=20, unique = true)
	private String email;
	@Column(length=20 , nullable = false)
	private String password;
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private List<Book> bookList = new ArrayList<Book>();
	
	public Author() {
		
	}
	
	public Author(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Book> getBookList() {
		return bookList;
	}
	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
	
	public void addBook(Book book)
	{
		bookList.add(book);
		book.setAuthor(this);
	}
	
	public void removeBook(Book book)
	{
		bookList.remove(book);
		book.setAuthor(null);
	}
	
	@Override
	public String toString() {
		return "Author [ Id=" +this.getId() +"firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
	

}
