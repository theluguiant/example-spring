package com.springboot.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import com.springboot.apirest.models.entity.Region;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="clients")
public class Client implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Size(min=4, max=12)
	@Column(nullable=false)
	private String name;
	
	@NotEmpty
	@Size(min=4, max=12)
	@Column(nullable=false)
	private String last;
	
	@NotEmpty
	@Email
	@Column(nullable=false, unique=true)
	private String email;
	private boolean active;
	
	@Column(name="active_at")
	@Temporal(TemporalType.DATE)
	private Date activeAt;
	
	@Column(name="deactive_at")
	@Temporal(TemporalType.DATE)
	private Date deactiveAt;
	
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	@Column(name="update_at")
	@Temporal(TemporalType.DATE)
	private Date updateAt;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="region_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Region region_id;
	
	@PrePersist
	public void prePersist() {
		createAt = new Date();
		activeAt = new Date();
		active = true;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Date getActiveAt() {
		return activeAt;
	}
	public void setActiveAt(Date activeAt) {
		this.activeAt = activeAt;
	}
	public Date getDeactiveAt() {
		return deactiveAt;
	}
	public void setDeactiveAt(Date deactiveAt) {
		this.deactiveAt = deactiveAt;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Date getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	
	public Region getRegion_id() {
		return region_id;
	}

	public void setRegion_id(Region region_id) {
		this.region_id = region_id;
	}

	private static final long serialVersionUID = 1L;

}
