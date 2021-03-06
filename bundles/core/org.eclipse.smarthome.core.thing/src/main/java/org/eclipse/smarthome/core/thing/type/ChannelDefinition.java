/**
 * Copyright (c) 2014,2018 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.smarthome.core.thing.type;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.smarthome.core.thing.Channel;
import org.eclipse.smarthome.core.thing.Thing;

/**
 * The {@link ChannelDefinition} class defines a {@link Channel} of a {@link ThingType}.
 * <p>
 * A {@link Channel} is part of a {@link Thing} that represents a functionality of it.
 * <p>
 * <b>Hint:</b> This class is immutable.
 *
 * @author Michael Grammling - Initial Contribution
 * @author Chris Jackson - Added properties and label/description
 * @author Dennis Nobel - Introduced ChannelTypeRegistry and channel type references
 */
public class ChannelDefinition {

    private final String id;
    private final ChannelTypeUID channelTypeUID;
    private final Map<String, String> properties;
    private final String label;
    private final String description;
    private final AutoUpdatePolicy autoUpdatePolicy;

    /**
     * Creates a new instance of this class with the specified parameters.
     *
     * @param id the identifier of the channel (must neither be null nor empty)
     * @param channelTypeUID the type UID of the channel (must not be null)
     * @throws IllegalArgumentException if the ID is null or empty, or the type is null
     */
    public ChannelDefinition(String id, ChannelTypeUID channelTypeUID) throws IllegalArgumentException {
        this(id, channelTypeUID, null, null, null, null);
    }

    /**
     * Creates a new instance of this class with the specified parameters.
     *
     * @param id the identifier of the channel (must neither be null nor empty)
     * @param channelTypeUID the type UID of the channel (must not be null)
     * @param properties the properties this Channel provides (could be null)
     * @param label the label for the channel to override channelType (could be null)
     * @param description the description for the channel to override channelType (could be null)
     * @throws IllegalArgumentException if the ID is null or empty, or the type is null
     */
    public ChannelDefinition(String id, ChannelTypeUID channelTypeUID, Map<String, String> properties, String label,
            String description) throws IllegalArgumentException {
        this(id, channelTypeUID, properties, label, description, null);
    }

    /**
     * Creates a new instance of this class with the specified parameters.
     *
     * @param id the identifier of the channel (must neither be null nor empty)
     * @param channelTypeUID the type UID of the channel (must not be null)
     * @param properties the properties this Channel provides (could be null)
     * @param label the label for the channel to override channelType (could be null)
     * @param description the description for the channel to override channelType (could be null)
     * @param autoUpdatePolicy the auto update policy for the channel to override from the thing type (could be null)
     * @throws IllegalArgumentException if the ID is null or empty, or the type is null
     */
    public ChannelDefinition(String id, ChannelTypeUID channelTypeUID, Map<String, String> properties, String label,
            String description, AutoUpdatePolicy autoUpdatePolicy) throws IllegalArgumentException {
        if ((id == null) || (id.isEmpty())) {
            throw new IllegalArgumentException("The ID must neither be null nor empty!");
        }

        if (channelTypeUID == null) {
            throw new IllegalArgumentException("The channel type must not be null");
        }

        if (properties != null) {
            this.properties = Collections.unmodifiableMap(properties);
        } else {
            this.properties = Collections.unmodifiableMap(new HashMap<String, String>(0));
        }

        this.id = id;
        this.channelTypeUID = channelTypeUID;
        this.label = label;
        this.description = description;
        this.autoUpdatePolicy = autoUpdatePolicy;
    }

    /**
     * Returns the identifier of the channel.
     *
     * @return the identifier of the channel (neither null, nor empty)
     */
    public String getId() {
        return this.id;
    }

    /**
     * Returns the type of the channel.
     *
     * @return the type of the channel (not null)
     */
    public ChannelTypeUID getChannelTypeUID() {
        return this.channelTypeUID;
    }

    /**
     * Returns the label (if set).
     * If no label is set, getLabel will return null and the default label for the {@link ChannelType} is used.
     *
     * @return the label for the channel. Can be null.
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Returns the description (if set).
     * If no description is set, getDescription will return null and the default description for the {@link ChannelType}
     * is used.
     *
     * @return the description for the channel. Can be null.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the properties for this {@link ChannelDefinition}
     *
     * @return the properties for this {@link ChannelDefinition} (not null)
     */
    public Map<String, String> getProperties() {
        return properties;
    }

    /**
     * Returns the {@link AutoUpdatePolicy} to use for this channel.
     *
     * @return the auto update policy
     */
    public AutoUpdatePolicy getAutoUpdatePolicy() {
        return autoUpdatePolicy;
    }

    @Override
    public String toString() {
        return "ChannelDefinition [id=" + id + ", type=" + channelTypeUID + ", properties=" + properties + "]";
    }

}
