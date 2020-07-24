package com.test.automation.ApiAutomation.responsepojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

	public class Datum {
		@SerializedName("id")
		@Expose
		private Integer id;
		@SerializedName("email")
		@Expose
		private String email;
		@SerializedName("first_name")
		@Expose
		private String firstName;
		@SerializedName("last_name")
		@Expose
		private String lastName;
		@SerializedName("avatar")
		@Expose
		private String avatar;

		public Integer getId() {
		return id;
		}

		public void setId(Integer id) {
		this.id = id;
		}

		public Datum withId(Integer id) {
		this.id = id;
		return this;
		}

		public String getEmail() {
		return email;
		}

		public void setEmail(String email) {
		this.email = email;
		}

		public Datum withEmail(String email) {
		this.email = email;
		return this;
		}

		public String getFirstName() {
		return firstName;
		}

		public void setFirstName(String firstName) {
		this.firstName = firstName;
		}

		public Datum withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
		}

		public String getLastName() {
		return lastName;
		}

		public void setLastName(String lastName) {
		this.lastName = lastName;
		}

		public Datum withLastName(String lastName) {
		this.lastName = lastName;
		return this;
		}

		public String getAvatar() {
		return avatar;
		}

		public void setAvatar(String avatar) {
		this.avatar = avatar;
		}

		public Datum withAvatar(String avatar) {
		this.avatar = avatar;
		return this;
		}

}
