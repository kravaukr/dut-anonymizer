package net.dut.anonymizer.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class User {

  private UUID id;
  private String login;
  private String password;
  private Set<String> allowedIp = new HashSet<>();
  private Set<String> roles = new HashSet<>();

  public User() {
  }

  public User(String login, String password) {
    this.login = login;
    this.password = password;
  }

  public User(UUID id, String login, String password, Set<String> allowedIp, Set<String> roles) {
    this.id = id;
    this.login = login;
    this.password = password;
    this.allowedIp = allowedIp;
    this.roles = roles;
  }

  public User(String login, String password, Set<String> allowedIp) {
    this(login, password);
    this.allowedIp = allowedIp;
  }

  public User(String login, String password, Set<String> allowedIp, Set<String> roles) {
    this(login, password, allowedIp);
    this.roles = roles;
  }

  public UUID getId() {
    return id;
  }

  public User setId(UUID id) {
    this.id = id;
    return this;
  }

  public String getLogin() {
    return login;
  }

  public User setLogin(String login) {
    this.login = login;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public User setPassword(String password) {
    this.password = password;
    return this;
  }

  public Set<String> getAllowedIp() {
    return allowedIp;
  }

  public User setAllowedIp(Set<String> allowedIp) {
    this.allowedIp = allowedIp;
    return this;
  }

  public Set<String> getRoles() {
    return roles;
  }

  public User setRoles(Set<String> roles) {
    this.roles = roles;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(allowedIp, user.allowedIp) && Objects.equals(roles, user.roles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, login, password, allowedIp, roles);
  }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", login='" + login + '\'' +
      ", password='" + password + '\'' +
      ", allowedIp=" + allowedIp +
      ", roles=" + roles +
      '}';
  }
}