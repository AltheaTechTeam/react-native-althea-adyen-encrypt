#if __has_include(<React/RCTBridgeModule.h>)
#import <React/RCTBridgeModule.h>
#else
#import "RCTBridgeModule.h"
#endif

@interface RNAltheaAdyenEncrypt : NSObject <RCTBridgeModule>

- (id)initWithLaunchOptions:(NSDictionary *)launchOptions key:(NSString *)key;

@end
  
