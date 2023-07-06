package pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//id,title(unique), price + ?????
@Entity
@Table(name = "books")
public class Book extends BaseEntity {
	@Column(name = "book_name", length=20)
	private String title;
	private double price;
	//book *------>1 Auther
	@ManyToOne
	@JoinColumn(name = "author_id")
	private Author author;
	
	public Book() {
		
	}
	public Book(String title, double price) {
		super();
		this.title = title;
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", price=" + price + "]";
	}
	

}
