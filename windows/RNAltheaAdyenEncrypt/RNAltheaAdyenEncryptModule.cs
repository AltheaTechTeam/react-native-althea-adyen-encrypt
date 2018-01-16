using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Com.Reactlibrary.RNAltheaAdyenEncrypt
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNAltheaAdyenEncryptModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNAltheaAdyenEncryptModule"/>.
        /// </summary>
        internal RNAltheaAdyenEncryptModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNAltheaAdyenEncrypt";
            }
        }
    }
}
