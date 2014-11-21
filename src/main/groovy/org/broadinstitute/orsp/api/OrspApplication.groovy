/*
 * The Broad Institute
 * SOFTWARE COPYRIGHT NOTICE AGREEMENT
 * This software and its documentation are copyright 2014 by the
 * Broad Institute/Massachusetts Institute of Technology. All rights are reserved.
 *
 * This software is supplied without any warranty or guaranteed support whatsoever. Neither
 * the Broad Institute nor MIT can be responsible for its use, misuse, or functionality.
 */
package org.broadinstitute.orsp.api

import com.mongodb.DB
import com.mongodb.Mongo
import groovy.util.logging.Slf4j
import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import org.broadinstitute.orsp.api.health.MongoHealthCheck
import org.broadinstitute.orsp.api.resources.IrbResource
import org.broadinstitute.orsp.api.resources.SampleCollectionResource

@Slf4j
class OrspApplication extends Application<OrspApplicationConfiguration> {

    private final String name = 'OrspApplication'

    public static void main(String[] args) throws Exception {
        new OrspApplication().run(args)
    }

    @Override
    public void run(OrspApplicationConfiguration configuration,
                    Environment environment) throws ClassNotFoundException {
        log.debug('Running ... ' + name)
        Mongo mongo = configuration.getMongo()
        MongoManaged mongoManaged = new MongoManaged(mongo)
        environment.lifecycle().manage(mongoManaged)
        DB mongoDb = mongo.getDB(configuration.mongodb);

        environment.jersey().register(new IrbResource(mongoDb))
        environment.jersey().register(new SampleCollectionResource(mongoDb))
        environment.healthChecks().register("MongoHealthCheck", new MongoHealthCheck(mongo))
    }

    @Override
    public void initialize(Bootstrap<OrspApplicationConfiguration> bootstrap) {

//        bootstrap.with {
//            addBundle guiceBundle
//        }

    }

//    private final GuiceBundle<OrspApplicationConfiguration> guiceBundle =
//            GuiceBundle.<OrspApplicationConfiguration>newBuilder()
//                    .setConfigClass(OrspApplicationConfiguration.class)
//                    .build()

}