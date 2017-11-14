package net.dut.anonymizer.api;

import akka.http.javadsl.server.Route;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.util.UUID;

import static akka.http.javadsl.marshallers.jackson.Jackson.marshaller;
import static akka.http.javadsl.model.StatusCodes.OK;
import static akka.http.javadsl.server.Directives.*;
import static akka.http.javadsl.server.PathMatchers.segment;
import static org.slf4j.LoggerFactory.getLogger;

public class PinRoute {

  private static final org.slf4j.Logger log = getLogger(PinRoute.class);

  public final Route route;

  private static final String pinAlgorithm = "MD5";
  private static final String salt = "secret";


  public PinRoute(final UserAuthenticator authenticator) {

    this.route = route(
      route(pathPrefix(segment("dut").slash("anonymizer").slash("api").slash("pin").slash(segment()), pin ->
          authenticator.authenticate.apply(user ->
            route(
              post(() ->
                getPinHash(pin)
              )
            )
          )
        )
      )
    );
  }


  public static Route getPinHash(final String pin) {
    UUID uuid = UUID.randomUUID();
    MessageDigest digest = DigestUtils.getDigest(pinAlgorithm);
    digest.update((pin + salt).getBytes());
    String hash = Hex.encodeHexString(digest.digest());

    log.info("[{}] OK, complete json, code: {}, body: {}", uuid, OK.intValue(), "pin: " + pin + "= hash: " + hash);

    return complete(OK, hash, marshaller());

  }
}