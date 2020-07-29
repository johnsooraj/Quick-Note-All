@Bean
public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {
        System.out.println("Let's inspect the beans provided by Spring Boot:");
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
        // OR
        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

    };