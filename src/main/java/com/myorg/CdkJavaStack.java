package com.myorg;

//import com.github.eladb.dynamotableviewer.TableViewer;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.apigateway.LambdaRestApi;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.Runtime;

public class CdkJavaStack extends Stack {
    public CdkJavaStack(final Construct parent, final String id) {
        this(parent, id, null);
    }

    public CdkJavaStack(final Construct parent, final String id, final StackProps props) {
        super(parent, id, props);

        //Add a new lambda resource to the stack
        final Function hello = Function.Builder.create(this,  "HelloHandler")
        		.runtime(Runtime.NODEJS_10_X)  // execution environment
        		.code(Code.fromAsset("lambda")) //code loaded from the lambda directory
        		.handler("hello.handler")      //file name is "hello", function name is "handler"
        		.build();
        		
        //Defines the hitcounter resource
        final HitCounter helloWithCounter = new HitCounter(this, "HelloHitCounter", HitCounterProps.builder()
        		.downstream(hello)
        		.build());
        
        
        //Defines an API Gateway REST API resource backed by the "hello" lambda function
        LambdaRestApi.Builder.create(this,  "EndPoint")
        .handler(helloWithCounter.getHandler())
        .build();
        
        //Defines a viewer for the HitCounts table
        /*TableViewer.Builder.create(this, "ViewHitCount")
        .title("Hello Hits")
        .table(helloWithCounter.getTable())
        .build();*/
        
    }
}

