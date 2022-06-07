package com.amido.graphqltest.graphql.resolver;

import com.amido.graphqltest.domain.Item;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

@Component
public class ItemResolver implements GraphQLResolver<Item> {
}
