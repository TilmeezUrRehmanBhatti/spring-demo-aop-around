# AOP: @Around Advice Type

**@Around Advice - Interaction**

<img src="https://user-images.githubusercontent.com/80107049/189789069-9be1522a-a095-4c7c-8526-7099847b5fcc.png" width="500" />


+ @Around: like a combination of @Before and @After, But gives you more fine-grained control

**Use Cases**
+ Most common: logging, auditing, security
+ Pre-processing and post-processing data
+ Instrumentation / profiling code
  + How long does it take for a section of code to run?

+ Managing exceptions
  + Swallow / handle / stop exceptions

**Sequence Diagram**
<img src="https://user-images.githubusercontent.com/80107049/189789170-a9ed434b-3f55-41c0-89e5-70f0d06c9104.png" width="500"/>


**ProceedingJointPoint**
+ When using @Around advice
+ You will get a reference to a **"proceeding join point**
+ This is a handle to the **target method**
+ Your code can the **proceeding join point** to execute **target method**


**Example**
+ Create an advice for instrumentation / profiling code
  + How long does it takes for a section of code to run?

<img src="https://user-images.githubusercontent.com/80107049/189789210-e642c79f-1cb4-45bb-828e-a77092756900.png" width="500" />      




<img src="https://user-images.githubusercontent.com/80107049/189789242-43ffc99a-183d-4eee-b2d8-61b09154f4cc.png" width="800" />



**Development Process - @Around**
1. Prep Work
2. Create TrafficFortuneService
3. Update main app to call TraficFortuneService
4. Add @Around advice

