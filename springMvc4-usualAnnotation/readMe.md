## Spring MVC常用注解有以下几个：
#### 1. @Controller
@Controller注解在类上，表明这个类是Spring MVC里面的Controller，将其声明为Spring的一个Bean，DispatcherServlet会自动扫描注解了此注解的类，并将请求映射到注解了@RequestMapping的方法上，这里特别指出，在声明普通Bean的时候，使用@Component，@Service，@Repository和@Controller是等同的，因为@Controller，@Service，@Repository都组合了@Component元注解，但在Spring MVC声明控制器Bean的时候，只能使用@Controller。

#### 2. @RequestMapping
@RequestMapping注解是用来映射Web请求(访问路径和参数)，处理类和方法的。@RequestMapping可注解在类或者方法上。注解在方法上的@RequestMapping路径会继承注解在类上的路径，@RequestMapping支持Servlet的request和response作为参数，也支持对request和response的媒体类型进行配置。

#### 3.@ResponseBody
@ResponseBody支持将返回值放在response体内，而不是返回一个页面。我们在很多基于Ajax程序的时候，可以以此注解返回数据而不是页面；此注解可放置在返回值前或者方法上。

#### 4. @RequestBody
@RequestBod允许request的参数在request体内，而不是直接链接在地址后面。此注解放置在参数前。

#### 5. @PathVariable
@PathVariable用来接收路径参数，如/news/001，可接收001作为参数，此注解放置在参数前。

#### 6.@RestController
@RestController是一个组合注解，组合了@Controller和@ResponseBody，这就意味着当你只开发一个和页面交互数据的控制的时候，需要使用此注解。若没有此注解，要想实现上述功能，则需要自己在代码中加@Controller和@ResponseBody两个注解。