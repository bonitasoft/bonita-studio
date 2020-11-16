/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.connectors.ui.wizard.custom.twitter;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterTools {

    private Twitter twitter;

    public TwitterTools(String consumerKey, String consumerSecret,
            String accessToken, String accessTokenSecret) {
        final ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey(consumerKey);
        cb.setOAuthConsumerSecret(consumerSecret);
        cb.setOAuthAccessToken(accessToken);
        cb.setOAuthAccessTokenSecret(accessTokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    public TwitterTools(String consumerKey, String consumerSecret,
            String accessToken, String accessTokenSecret, String proxyHost,
            int proxyPort) {
        final ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey(consumerKey);
        cb.setOAuthConsumerSecret(consumerSecret);
        cb.setOAuthAccessToken(accessToken);
        cb.setOAuthAccessTokenSecret(accessTokenSecret);
        if (!proxyHost.isEmpty()) {
            cb.setHttpProxyHost(proxyHost);
            cb.setHttpProxyPort(proxyPort);
        }
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    public TwitterTools(String consumerKey, String consumerSecret,
            String accessToken, String accessTokenSecret, String proxyHost,
            int proxyPort, String proxyUser, String proxyPass) {
        final ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey(consumerKey);
        cb.setOAuthConsumerSecret(consumerSecret);
        cb.setOAuthAccessToken(accessToken);
        cb.setOAuthAccessTokenSecret(accessTokenSecret);
        if (!proxyHost.isEmpty()) {
            cb.setHttpProxyHost(proxyHost);
            cb.setHttpProxyPort(proxyPort);
            if (!proxyUser.isEmpty() && !proxyPass.isEmpty()) {
                cb.setHttpProxyUser(proxyUser);
                cb.setHttpProxyPassword(proxyPass);
            }
        }
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    public TwitterException testConnection() {
        try {
            twitter.getAccountSettings();
        } catch (TwitterException e) {
            return e;
        }
        return null;
    }

    public List<String> getFollowersAndFriendsName() {
        List<String> returnList = new ArrayList<>();
        ResponseList<User> l;
        try {
            l = twitter.getFollowersList(twitter.getId(), -1);
            for (User user : l) {
                returnList.add(user.getScreenName());
            }
            l = twitter.getFriendsList(twitter.getId(), -1);
            for (User user : l) {
                if (!returnList.contains(user.getScreenName())) {
                    returnList.add(user.getScreenName());
                }
            }
            return returnList;
        } catch (IllegalStateException e) {
            BonitaStudioLog.log(e.getMessage());
            return returnList;
        } catch (TwitterException e) {
            BonitaStudioLog.log(e.getMessage());
            return returnList;
        }
    }

    public List<String> searchUser(String name) {
        List<String> returnList = new ArrayList<>();
        try {
            ResponseList<User> l = twitter.searchUsers(name, -1);
            for (User user : l) {
                returnList.add(user.getScreenName());
            }
            return returnList;
        } catch (TwitterException e) {
            BonitaStudioLog.log(e.getMessage());
            return returnList;
        }
    }

    public User getUser(String name) {
        try {
            ResponseList<User> users = twitter.searchUsers(name, -1);
            if (users.size() > 0) {
                return users.get(0);
            }
            return null;
        } catch (TwitterException e) {
            BonitaStudioLog.log(e.getMessage());
            return null;
        }
    }

    public List<String> getLastweet(String userScreenName) {
        List<String> returnList = new ArrayList<>();
        try {

            ResponseList<Status> status = twitter
                    .getUserTimeline(userScreenName);
            for (Status s : status) {
                returnList.add(s.getText());
            }
            return returnList;
        } catch (TwitterException e) {
            BonitaStudioLog.log(e.getMessage());
            return returnList;
        }
    }

}
