package com.myorg;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
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
        		
        
    }
}
