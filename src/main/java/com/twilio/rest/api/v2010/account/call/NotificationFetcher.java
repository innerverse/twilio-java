/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account.call;

import com.twilio.base.Fetcher;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class NotificationFetcher extends Fetcher<Notification> {
    private String pathAccountSid;
    private final String pathCallSid;
    private final String pathSid;

    /**
     * Construct a new NotificationFetcher.
     *
     * @param pathCallSid The Call SID of the resource to fetch
     * @param pathSid The unique string that identifies the resource
     */
    public NotificationFetcher(final String pathCallSid,
                               final String pathSid) {
        this.pathCallSid = pathCallSid;
        this.pathSid = pathSid;
    }

    /**
     * Construct a new NotificationFetcher.
     *
     * @param pathAccountSid The SID of the Account that created the resource to
     *                       fetch
     * @param pathCallSid The Call SID of the resource to fetch
     * @param pathSid The unique string that identifies the resource
     */
    public NotificationFetcher(final String pathAccountSid,
                               final String pathCallSid,
                               final String pathSid) {
        this.pathAccountSid = pathAccountSid;
        this.pathCallSid = pathCallSid;
        this.pathSid = pathSid;
    }

    /**
     * Make the request to the Twilio API to perform the fetch.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Fetched Notification
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Notification fetch(final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.GET,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.pathAccountSid + "/Calls/" + this.pathCallSid + "/Notifications/" + this.pathSid + ".json",
            client.getRegion()
        );

        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Notification fetch failed: Unable to connect to server");
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

        return Notification.fromJson(response.getStream(), client.getObjectMapper());
    }
}