package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Reimb {
	
	private int reimbId;
	private double amount;
	private Timestamp submitted;
	private Timestamp resolved;
	private String descr;
	private int author;
	private int resolver;
	private int status;
	private int type;	

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getReimbId() {
		return reimbId;
	}

	public Reimb() {
		// TODO Auto-generated constructor stub
	}

	public Reimb(double amount, String descr, int author, int status, int type) {
		super();
		this.amount = amount;
		this.submitted = Timestamp.valueOf(LocalDateTime.now());
		resolved = null;
		this.descr = descr;
		this.author = author;
		this.status = status;
		this.type = type;
	}
	
	public Reimb(double amount, Timestamp submitted, Timestamp resolved, String descr, int author,
			int resolver, int status, int type) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.descr = descr;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}
	
	public Reimb(int reimbId, double amount, Timestamp submitted, Timestamp resolved, String descr, int author,
			int resolver, int status, int type) {
		super();
		this.reimbId = reimbId;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.descr = descr;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Reimb [reimbId=" + reimbId + ", amount=" + amount + ", submitted=" + submitted + ", resolved="
				+ resolved + ", descr=" + descr + ", author=" + author + ", resolver=" + resolver + ", status=" + status
				+ ", type=" + type + "]";
	}
	
}
