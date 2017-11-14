package net.dut.anonymizer.api;

import akka.http.javadsl.server.Route;
import akka.http.javadsl.server.directives.SecurityDirectives;
import net.dut.anonymizer.model.User;

import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import static akka.http.javadsl.server.Directives.authenticateBasicAsync;
import static java.util.concurrent.CompletableFuture.completedFuture;


public class UserAuthenticator {

    public final Function<Function<User, Route>, Route> authenticate;


    public UserAuthenticator(String login, String password) {

        final Function<Optional<SecurityDirectives.ProvidedCredentials>, CompletionStage<Optional<User>>> userAuthenticator = credentials ->
                completedFuture(credentials.flatMap(c -> getUser(c.identifier(), login, password).filter(user -> c.verify(user.getPassword()))));

        authenticate = inner ->
                authenticateBasicAsync("cpa realm", userAuthenticator, user ->
                        inner.apply(user));
    }

    public Optional<User> getUser(String identifier, String login, String password) {
        return (identifier.equals(login)) ? Optional.of(new User(login, password)) : Optional.empty();
    }
}
