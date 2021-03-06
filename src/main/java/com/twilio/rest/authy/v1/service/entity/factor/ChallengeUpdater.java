/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.authy.v1.service.entity.factor;

import com.twilio.base.Updater;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
public class ChallengeUpdater extends Updater<Challenge> {
    private final String pathServiceSid;
    private final String pathIdentity;
    private final String pathFactorSid;
    private final String pathSid;
    private String authPayload;

    /**
     * Construct a new ChallengeUpdater.
     *
     * @param pathServiceSid Service Sid.
     * @param pathIdentity Unique identity of the Entity
     * @param pathFactorSid Factor Sid.
     * @param pathSid A string that uniquely identifies this Challenge, or `latest`.
     */
    public ChallengeUpdater(final String pathServiceSid,
                            final String pathIdentity,
                            final String pathFactorSid,
                            final String pathSid) {
        this.pathServiceSid = pathServiceSid;
        this.pathIdentity = pathIdentity;
        this.pathFactorSid = pathFactorSid;
        this.pathSid = pathSid;
    }

    /**
     * The optional payload needed to verify the Challenge. E.g., a TOTP would use
     * the numeric code..
     *
     * @param authPayload Optional payload to verify the Challenge
     * @return this
     */
    public ChallengeUpdater setAuthPayload(final String authPayload) {
        this.authPayload = authPayload;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated Challenge
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Challenge update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.AUTHY.toString(),
            "/v1/Services/" + this.pathServiceSid + "/Entities/" + this.pathIdentity + "/Factors/" + this.pathFactorSid + "/Challenges/" + this.pathSid + "",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Challenge update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }

            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }

        return Challenge.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (authPayload != null) {
            request.addPostParam("AuthPayload", authPayload);
        }
    }
}