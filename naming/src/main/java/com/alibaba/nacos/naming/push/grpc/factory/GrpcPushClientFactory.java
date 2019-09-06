/*
 * Copyright (C) 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.nacos.naming.push.grpc.factory;

import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.naming.push.SubscribeMetadata;
import com.alibaba.nacos.core.remoting.grpc.interactive.GrpcRequestStreamInteractive;
import com.alibaba.nacos.naming.push.AbstractPushClient;
import com.alibaba.nacos.naming.push.DataSource;
import com.alibaba.nacos.naming.push.IPushClientFactory;
import com.alibaba.nacos.naming.push.grpc.GrpcPushClient;

/**
 * @author pbting
 * @date 2019-09-04 2:24 PM
 */
public class GrpcPushClientFactory implements IPushClientFactory {

    @Override
    public AbstractPushClient newPushClient(SubscribeMetadata subscribeMetadata, DataSource dataSource) {

        if (subscribeMetadata.getPort() > Constants.PORT_IDENTIFY_GRPC_BIGGER) {
            return new GrpcPushClient(subscribeMetadata, dataSource, null);
        }
        return null;
    }

    @Override
    public <T> AbstractPushClient newPushClient(SubscribeMetadata subscribeMetadata, DataSource dataSource, T pusher) {

        if (subscribeMetadata.getPort() > Constants.PORT_IDENTIFY_GRPC_BIGGER) {
            return new GrpcPushClient(subscribeMetadata, dataSource, (GrpcRequestStreamInteractive) pusher);
        }
        return null;
    }
}