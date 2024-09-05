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

package net.devh.boot.grpc.client.autoconfigure;

import static java.util.Objects.requireNonNull;

import java.time.Duration;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.MethodDescriptor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.channelfactory.GrpcChannelConfigurer;
import net.devh.boot.grpc.client.config.GrpcChannelsProperties;
import net.devh.boot.grpc.client.interceptor.DeadlineSetupClientInterceptor;

/**
 * The deadline autoconfiguration for the client.
 *
 * <p>
 * You can disable this config by using:
 * </p>
 *
 * <pre>
 * <code>@ImportAutoConfiguration(exclude = GrpcClientDeadlineAutoConfiguration.class)</code>
 * </pre>
 *
 * @author Sergei Batsura (batsura.sa@gmail.com)
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(GrpcClientAutoConfiguration.class)
public class GrpcClientDeadlineAutoConfiguration {

    /**
     * Creates a {@link GrpcChannelConfigurer} bean with interceptor that will call withDeadlineAfter with deadline from
     * props.
     *
     * @param props The properties for deadline configuration.
     * @return The StubTransformer bean with interceptor if deadline is configured.
     * @see DeadlineSetupClientInterceptor
     */
    @Bean
    GrpcChannelConfigurer deadlineGrpcChannelConfigurer(final GrpcChannelsProperties props) {
        requireNonNull(props, "properties");

        return (channel, name) -> {
            Duration deadline = props.getChannel(name).getDeadline();
            if (deadline != null && deadline.toMillis() > 0L) {
                channel.intercept(new DeadlineSetupClientInterceptor(deadline));
            }
        };
    }

}
