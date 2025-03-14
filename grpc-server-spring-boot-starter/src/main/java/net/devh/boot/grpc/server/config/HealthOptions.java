/*
 * Copyright (c) 2016-2024 The gRPC-Spring Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.devh.boot.grpc.server.config;

import lombok.Data;

/**
 * GRPC Health service options.
 */
@Data
public class HealthOptions {

    /**
     * Implementation of gRPC health service. Defaults to {@link HealthType#GRPC GRPC}. To disable health service set to
     * {@link HealthType#NONE NONE}.
     *
     * @see net.devh.boot.grpc.server.autoconfigure.GrpcHealthServiceAutoConfiguration
     * @param type The implementation of gRPC health service.
     * @return GRPC, ACTUATOR or NONE.
     */
    private HealthType type = HealthType.GRPC;
}
