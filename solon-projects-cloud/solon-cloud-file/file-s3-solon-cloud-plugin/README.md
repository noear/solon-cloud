### 配置示例

```yaml
solon.cloud.file.s3.file:
  default: 'demo1_bucket' #默认
  buckets:
    demo1_bucket: #所用存储桶( bucket )，必须都先配置好 //且 bucket 须手动先建好
      endpoint: 'https://obs.cn-southwest-2.myhuaweicloud.com' #通过协议，表达是否使用 https?
      regionId: ''
      accessKey: 'xxxx'
      secretKey: 'xxx'
    demo2_bucket:
      endpoint: 'D:/img' # 或 '/data/sss/files' #表示为本地（非本地的，要 http:// 或 https:// 开头）
    demo3_bucket:
      endpoint: 'http://s3.ladydaily.com'
      regionId: ''
      accessKey: 'xxxx'
      secretKey: 'xxx'
      
```

If not provided accessKey and secretKey, then use aws default credentials provided. 
you need run on AWS services and use AWS IAM authentications.

其它 s3 bucket 属性支持
```yml
demo1_bucket:
  pathStyleAccessEnabled: false #minio 一般要设为 true
  chunkedEncodingDisabled: false
  accelerateModeEnabled: false
  payloadSigningEnabled: false
  dualstackEnabled: false
  forceGlobalBucketAccessEnabled: false
  useArnRegionEnabled: false
  regionalUsEast1EndpointEnabled: false
```

### 文件管理 - 简单示例：上传，下载，删除

如果需要代码创建 bucket ，可通过 BucketUtils 工具创建

```java
@Mapping("file")
@Controller
public class FileController {
    @Get
    @Mapping("{bucket}/**") 
    public DownloadedFile get(Context ctx, String bucket) { //文件下载
        String pathPrefix = "/file/" + bucket + "/";
        String fileName = ctx.path().substring(pathPrefix.length());

        Media media = CloudClient.file().get(bucket, fileName);
        return new DownloadedFile(media.contentType(), media.body(), fileName);
    }
    
    @Post
    @Mapping("{bucket}/**")
    public Result post(Context ctx, String bucket, UploadedFile file) { //文件上传
        String pathPrefix = "/file/" + bucket + "/";
        String fileName = ctx.path().substring(pathPrefix.length());

        Media media = new Media(file.getContent(), file.getContentType());
        return CloudClient.file().put(bucket, fileName, media);
    }

    @Delete 
    @Mapping("{bucket}/**")
    public Result delete(Context ctx, String bucket) { //文件删除
        String pathPrefix = "/file/" + bucket + "/";
        String fileName = ctx.path().substring(pathPrefix.length());

        return CloudClient.file().delete(bucket, fileName);
    }
}
```

