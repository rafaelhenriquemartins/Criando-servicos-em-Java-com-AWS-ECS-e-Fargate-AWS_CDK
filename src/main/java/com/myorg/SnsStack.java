package com.myorg;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.events.targets.SnsTopic;
import software.amazon.awscdk.services.sns.Topic;
import software.amazon.awscdk.services.sns.subscriptions.EmailSubscription;

public class SnsStack extends Stack {
    private final SnsTopic productEventsTopic;

    public SnsStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public SnsStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);


        productEventsTopic = SnsTopic.Builder.create(Topic.Builder.create(this, "productEventsTopic")
                .topicName("product-events")
                .build()) //construindo o topico
                .build(); // construindo o SNS


        productEventsTopic.getTopic().addSubscription(EmailSubscription.Builder.create("rafael_hmartins@outlook.com")
                .json(true)
                .build());
    }

    public SnsTopic getProductEventsTopic() {
        return productEventsTopic;
    }
}
