/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.taskrouter.v1.workspace;

import com.twilio.base.Updater;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class TaskChannelUpdater extends Updater<TaskChannel> {
    private final String pathWorkspaceSid;
    private final String pathSid;
    private String friendlyName;
    private Boolean channelOptimizedRouting;

    /**
     * Construct a new TaskChannelUpdater.
     *
     * @param pathWorkspaceSid The unique ID of the Workspace that this TaskChannel
     *                         belongs to.
     * @param pathSid The unique ID for this TaskChannel.
     */
    public TaskChannelUpdater(final String pathWorkspaceSid,
                              final String pathSid) {
        this.pathWorkspaceSid = pathWorkspaceSid;
        this.pathSid = pathSid;
    }

    /**
     * Toggle the FriendlyName for the TaskChannel.
     *
     * @param friendlyName Toggle the FriendlyName for the TaskChannel
     * @return this
     */
    public TaskChannelUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * A boolean that if true; mean that the channel will prioritize workers that
     * have been idle.
     *
     * @param channelOptimizedRouting If true then prioritize longest idle workers
     * @return this
     */
    public TaskChannelUpdater setChannelOptimizedRouting(final Boolean channelOptimizedRouting) {
        this.channelOptimizedRouting = channelOptimizedRouting;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated TaskChannel
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public TaskChannel update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.TASKROUTER.toString(),
            "/v1/Workspaces/" + this.pathWorkspaceSid + "/TaskChannels/" + this.pathSid + "",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("TaskChannel update failed: Unable to connect to server");
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

        return TaskChannel.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

        if (channelOptimizedRouting != null) {
            request.addPostParam("ChannelOptimizedRouting", channelOptimizedRouting.toString());
        }
    }
}